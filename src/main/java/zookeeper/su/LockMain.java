package zookeeper.su;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class LockMain {
    private static final String connectString = "192.168.50.32:2181";
    private static final String path = "/shared_lock/locked";

    static CuratorFramework client = null;

    public static void main(String[] args) {
        Create_Session_Sample();
    }
    
    
    public static CuratorFramework CreateSession(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(connectString, 5000, 3000, retryPolicy);
        return client;
    }
    

    public static void Create_Session_Sample() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(connectString, 5000, 3000, retryPolicy);
        client.start();
        try {
            // client.create().forPath(path,"init".getBytes());
            
            // 如果父节点不存在 递归创建     PERSISTENT:持久节点     EPHEMERAL:临时节点
            //client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, "init".getBytes());
            
            //System.out.println("==================创建成功");
            
            //client.setData().forPath(path, "crud".getBytes());
            //Stat stat=new Stat();
            //client.getData().storingStatIn(stat);
            
            System.out.println("=============读取数据============"+new String(client.getData().forPath(path)));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
