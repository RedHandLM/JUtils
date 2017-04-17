package zookeeper.su;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.zookeeper.CreateMode;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉Curator 异步api
 *
 * @author shichang.liu
 * @date 2017年4月17日下午3:47:31
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Create_Node_Background_Sample {

    static final String path = "/shared_lock/locked";

    static CuratorFramework client = LockMain.CreateSession();

    static CountDownLatch semaphore = new CountDownLatch(2);

    static ExecutorService tp = Executors.newFixedThreadPool(2);    //自定义executors

    public static void main(String[] args) {
        client.start();
        System.out.println("current Thread:  "+Thread.currentThread().getName());
        try {
            client.create()
                  .creatingParentsIfNeeded()
                  .withMode(CreateMode.PERSISTENT)
                  .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("event[code: " + event.getResultCode() + " ,type: " + event.getType() + " ]");
                        System.out.println("写入的参数是:====="+new String(client.getData().forPath(path)));
                        semaphore.countDown();
                    }
            }, tp).forPath(path, "init".getBytes());
            
            
            client.create()
                  .creatingParentsIfNeeded()
                  .withMode(CreateMode.PERSISTENT)
                  .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                        System.out.println("event[code: " + event.getResultCode() + " ,type: " + event.getType() + " ]");
                        System.out.println("写入的参数是:====="+new String(client.getData().forPath(path)));  
                        semaphore.countDown();
                    }
                  }).forPath(path,"init".getBytes());
            
            semaphore.await();
            tp.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
