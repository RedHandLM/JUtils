package zookeeper.su.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉JDK CyclicBarrier实现赛跑
 *
 * @author shichang.liu
 * @date 2017年4月17日下午5:32:32
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Recipes_CyclicBarrier {

    static CyclicBarrier barrier = new CyclicBarrier(3);

    public static void main(String[] args) {
        ExecutorService exector = Executors.newFixedThreadPool(3);
        exector.submit(new Thread(new Runner("1号选手")));
        exector.submit(new Thread(new Runner("2号选手")));
        exector.submit(new Thread(new Runner("3号选手")));
    }

}

class Runner implements Runnable {
    private String name;

    public Runner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " 准备好了");
        try {
            Recipes_CyclicBarrier.barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(name + " 起跑！");
    }

}