package lsc.disruptor.simple_demo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OrdinaryMain {
    /**
     * 普通情况未加锁：341 普通情况加锁：325
     */

    public static void main(String[] args) {
        Executor ex = Executors.newScheduledThreadPool(2);
        final long l = 5000;
        long currentTime = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                computer(l);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                computer(l);
            }
        });
        ex.execute(thread1);
        ex.execute(thread2);
        System.out.println(System.currentTimeMillis() - currentTime);
    }

    public static synchronized void computer(long l) {
        for (long i = 0; i < l; i++) {
            System.out.println(i);
        }
    }
}
