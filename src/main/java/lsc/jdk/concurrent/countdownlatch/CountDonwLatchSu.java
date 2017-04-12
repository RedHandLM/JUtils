package lsc.jdk.concurrent.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDonwLatchSu {
    final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 线程同步辅助类
    // countDown() 当前线程调用此方法，计数减一
    // await() 调用此方法会一直阻塞当前线程 一直到计时器值为0
    static CountDownLatch conuntDown = new CountDownLatch(4);// 10人工人协同工作

    public static void main(String[] args) {
        try {
            Worker worker1 = new Worker("One=", 5000, conuntDown);
            Worker worker2 = new Worker("Two=", 8000, conuntDown);
            worker1.start();
            worker2.start();
            conuntDown.await();
            System.out.println("all work done at" + format.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Worker extends Thread {
        String workName;
        int workTime;
        CountDownLatch latch;

        public Worker(String workName, int workTime, CountDownLatch latch) {
            this.workName = workName;
            this.workTime = workTime;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println("worker: " + workName + " do work at " + format.format(new Date()));
                doWork();// 工作
                System.out.println("worker: " + workName + " do work complete at " + format.format(new Date()));
            } finally {
                latch.countDown();// 工作完成 计数-1
            }
        }

        private void doWork() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
