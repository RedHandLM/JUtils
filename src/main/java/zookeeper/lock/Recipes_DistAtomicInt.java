package zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.RetryNTimes;

import zookeeper.su.LockMain;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉使用curator实现分布式计数器
 *
 * @author shichang.liu
 * @date 2017年4月17日下午5:24:47
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Recipes_DistAtomicInt {

    static String count_path = "/curator_recipes_distatomicint_path";

    static CuratorFramework client = LockMain.CreateSession();

    public static void main(String[] args) {
        try {
            client.start();
            DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(
                        client, count_path, new RetryNTimes(3, 1000));
            AtomicValue<Integer> rc = atomicInteger.add(8);
            System.out.println("Result：" + rc.succeeded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
