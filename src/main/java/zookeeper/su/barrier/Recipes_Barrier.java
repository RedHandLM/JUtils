package zookeeper.su.barrier;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;

import zookeeper.su.LockMain;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉使用curator实现分布式barrier
 *
 * @author shichang.liu
 * @date 2017年4月17日下午5:41:15
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Recipes_Barrier {

    static String barrierPath = "/curator_recipes_barrier_path";

    static DistributedBarrier barrier;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        CuratorFramework client = LockMain.CreateSession();
                        client.start();
                        barrier = new DistributedBarrier(client, barrierPath);
                        System.out.println(Thread.currentThread().getName() + "号barrier设置");
                        barrier.setBarrier();
                        barrier.waitOnBarrier();
                        System.out.println("启动。。。。。。。");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(2000);
            barrier.removeBarrier();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
