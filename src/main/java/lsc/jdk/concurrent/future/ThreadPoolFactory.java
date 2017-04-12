package lsc.jdk.concurrent.future;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolFactory {
	private static ThreadPoolFactory factory = new ThreadPoolFactory();
	
	//线程池
	private ThreadPoolExecutor pool;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized void init(int coreWorkerNum, int maxWorkerNum,
			int queueSize, long keepAliveTime) {
		this.pool = new ThreadPoolExecutor(
		        coreWorkerNum, 
		        maxWorkerNum,
				keepAliveTime, 
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue(queueSize, true), 
				new Reject());
	}

	public synchronized ThreadPoolExecutor getThreadPool() {
		return this.pool;
	}

	public static ThreadPoolFactory getInstance() {
		return factory;
	}

	class Reject implements RejectedExecutionHandler {
		Reject() {
		}

		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			try {
                throw new Exception("线程池已满，无法接收新的任务.");
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
	
}
