package lsc.disruptor.performanceCompare;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lsc.disruptor.performanceCompare.bean.LogEvent;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉针对LinkedBlockingQueue的压测类,实现了一个简单的生产者-消费者模式，一条线程负责插入，另外一条线程负责读取
 * 
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LinkedBlockingQueueTest {
    public static int eventNum = 5000000;

    public static void main(String[] args) {
        final BlockingQueue<LogEvent> queue = new LinkedBlockingQueue<LogEvent>();
        final long startTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (i < eventNum) {
                    LogEvent logEvent = new LogEvent(i, "c" + i);
                    try {
                        queue.put(logEvent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                int k = 0;
                while (k < eventNum) {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                long endTime = System.currentTimeMillis();
                System.out.println("costTime = " + (endTime - startTime) + "ms");
            }
        }).start();
    }
}