package lsc.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorFrameworkUtil {

    //注册zk地址，命名前缀解析
    private static final String PREFIX = "://";
    //zk连接客户端，curator框架
    private static Map<String, CuratorFramework> CLIENTS = new HashMap<String, CuratorFramework>(2);
    
    public static CuratorFramework getInstance(String zkHost) {
        CuratorFramework client = getFromClients(zkHost);
        if(client == null) {
            client = init(zkHost);
            setToClients(zkHost, client);
        }
        return client;
    }

    private static CuratorFramework getFromClients(String zkHost) {
        return CLIENTS.get(zkHost);
    }
    private static void setToClients(String zkHost, CuratorFramework client) {
        if(client != null) {
            CLIENTS.put(zkHost, client);
        }
    }
    private static String cleanZkHost(String host) {
        int index = -1;
        if(StringUtils.isNotBlank(host) && (index = host.indexOf(PREFIX)) >= 0) {
            return host.substring(index + PREFIX.length());
        }
        return host;
    }
    private static CuratorFramework init(String zookeeperHost) {
        String dubbo_registry = cleanZkHost(zookeeperHost);
        CuratorFramework client = null;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                //zookeeper连接地址和重试逻辑
                .connectString(dubbo_registry).retryPolicy(retryPolicy)
                //节点空间
                .namespace("watchtest").build();
        client.start();

        return client;
    }
}