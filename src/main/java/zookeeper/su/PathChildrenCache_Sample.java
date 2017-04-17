package zookeeper.su;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.zookeeper.CreateMode;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉子节点监听
 *
 * @author shichang.liu
 * @date 2017年4月17日下午4:41:06
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PathChildrenCache_Sample {
    static final String path = "/shared_lock";

    static CuratorFramework client = LockMain.CreateSession();

    public static void main(String[] args) {
        try {
            client.start();
            // client.delete().forPath(path);

            PathChildrenCache cache = new PathChildrenCache(client, path, true);
            cache.start(StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            System.out.println("CHILD_ADDED ," + event.getData());
                            break;
                        case CHILD_UPDATED:
                            System.out.println("CHILD_UPDATED ," + event.getData());
                            break;
                        case CHILD_REMOVED:
                            System.out.println("CHILD_REMOVED ," + event.getData());
                            break;

                        default:
                            break;
                    }

                }
            });
            // create
            client.create().withMode(CreateMode.PERSISTENT).forPath(path);
            Thread.sleep(1000);

            // create children node
            client.create().withMode(CreateMode.PERSISTENT).forPath(path + "/c1");
            Thread.sleep(1000);

            // delete children node
            client.delete().forPath(path + "/c1");
            Thread.sleep(1000);

            // delete current node
            client.delete().forPath(path);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
