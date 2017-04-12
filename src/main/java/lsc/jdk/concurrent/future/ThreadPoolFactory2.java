package lsc.jdk.concurrent.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工厂类
 * 
 * @author richerd
 *
 */
public class ThreadPoolFactory2 {

    private static ThreadPoolFactory2 factory = new ThreadPoolFactory2();

    // 线程池
    private ThreadPoolExecutor pool;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public synchronized void init(int coreWorkerNum, int maxWorkerNum, int queueSize, long keepAliveTime) {
        this.pool = new ThreadPoolExecutor(
                coreWorkerNum, 
                maxWorkerNum, 
                keepAliveTime, 
                TimeUnit.SECONDS, 
                new ArrayBlockingQueue(queueSize, true), 
                new Reject());
    }

    /*
     * 获取线程池的方法
     */
    public synchronized ThreadPoolExecutor getThreadPool() {
        return this.pool;
    }

    
    class Reject implements RejectedExecutionHandler {
        Reject() {

        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                throw new Exception("线程已满");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
