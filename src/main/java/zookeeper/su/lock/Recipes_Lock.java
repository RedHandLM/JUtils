package zookeeper.su.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import zookeeper.su.LockMain;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉使用Curator实现分布式锁功能
 *
 * @author shichang.liu
 * @date 2017年4月17日下午5:17:44
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Recipes_Lock {

    static String lock_path = "/curator_recipes_lock_path";

    static CuratorFramework client = LockMain.CreateSession();

    public static void main(String[] args) {
        client.start();
        final InterProcessMutex lock = new InterProcessMutex(client, lock_path);
        final CountDownLatch down = new CountDownLatch(1);
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                        lock.acquire();// 获取分布式锁
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss | SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单编号是：" + orderNo);
                    try {
                        lock.release();// 释放分布式锁
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        down.countDown();
    }

}
