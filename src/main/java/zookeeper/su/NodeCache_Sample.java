package zookeeper.su;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉任务监听
 *
 * @author shichang.liu
 * @date 2017年4月17日下午4:05:23
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class NodeCache_Sample {
    static final String path = "/shared_lock/locked";

    static CuratorFramework client = LockMain.CreateSession();

    public static void main(String[] args) {
        client.start();
        final NodeCache cache = new NodeCache(client, path, false);
        try {
            cache.start(Boolean.TRUE);//true 第一次启动就会从zookeeper读取节点数据  默认为false
            cache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("Node data update,new data : " + new String(cache.getCurrentData().getData()));
                }
            });
            client.setData().forPath(path, "u".getBytes());
            Thread.sleep(1000);
            client.delete().deletingChildrenIfNeeded().forPath(path);
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
