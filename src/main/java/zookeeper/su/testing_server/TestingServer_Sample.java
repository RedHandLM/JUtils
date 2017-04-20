package zookeeper.su.testing_server;

import org.apache.curator.framework.CuratorFramework;

import zookeeper.su.LockMain;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉TestingServer 简单示例
 *
 * @author shichang.liu
 * @date 2017年4月17日下午5:56:41
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TestingServer_Sample {

    static String path = "/shared_lock";

    static CuratorFramework client = LockMain.CreateSession();

    public static void main(String[] args) {
        try {
            client.start();
            // System.out.println(client.delete().deletingChildrenIfNeeded().forPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
