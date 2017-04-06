package lsc.util;

import lsc.clent.GgFrameworkClient;
import lsc.core.GgTable;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GgFrameworkUtil {

    private static Logger log = LoggerFactory.getLogger(GgFrameworkUtil.class);

    // 标记clent是否已经初始化
    public static boolean isInit = false;

    // 数据广播节点
    public static final String broadcaset_node = "/broadcaset_node";

    // 订阅监听节点
    public static final String notify_node = "/notify_node";

    // 注册zookeeper地址
    private static final String PREFIX = "://";

    public static final String FRAMEWORK_PREFIX = "GgFramework";

    private static CuratorFramework CLIENT = null;

    public static void initCuratorFramework(String zkHost) {
        if (CLIENT == null) {
            CLIENT = init(zkHost);
        }
    }

    private static CuratorFramework init(String zookeeperHost) {
        final String dubbo_registry = cleanZkHost(zookeeperHost);
        CuratorFramework client = CuratorFrameworkUtil.getInstance(dubbo_registry);
        // 添加客户端监听
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                if (newState.isConnected()) {
                    log.debug("[zookeeperHost] connected to zeekeeper :{}", dubbo_registry);
                    isInit = true;
                    GgFrameworkClient.addListenerFromCache();
                }

            }
        });
        return client;
    }

   
    /**
     * 数据修改监听节点创建
     * 
     * 在指定的广播节点进行数据监听
     */
    public static NodeCache broadcastNotifyNodeChanged(GgTable table) {

        checkZkTableForDbAndTable("org.common4s.zookeeper.util.CuratorUtil.broadcastNotifyNodeChanged(ZkTable)", table);

        String path = broadcaset_node + "/" + table.getDb() + "/" + table.getTable();
        createIfNotExists(path);
        return new NodeCache(GgFrameworkUtil.getClient(), path, false);
    }

    private static String cleanZkHost(String host) {
        int index = -1;
        if (StringUtils.isNotBlank(host) && (index = host.indexOf(PREFIX)) >= 0) {
            return host.substring(index + PREFIX.length());
        }
        return host;
    }

    private static void checkZkTableForDbAndTable(String method, GgTable table) throws IllegalArgumentException {
        if (!isZkTableDbAndTableValid(table)) {
            throw new IllegalArgumentException("使用【" + method + "】时必须设置ZkTable的db和table");
        }
    }

    public static CuratorFramework getClient() {
        return CLIENT;
    }

    // 判断表是否设置了 db和table
    public static boolean isZkTableDbAndTableValid(GgTable table) {
        if (table == null) {
            return false;
        }
        return StringTools.isNotBlank(table.getDb()) && StringTools.isNotBlank(table.getTable());
    }

    // 判断是否设置了 field和value
    public static boolean isZkTableFieldAndValueValid(GgTable table) {
        return StringTools.isNotBlank(table.getField()) && StringTools.isNotBlank(table.getValue());
    }

    /*
     * 节点创建确认 如果节点不存在，则创建
     */
    private static void createIfNotExists(String nodePath) {
        try {
            if (CLIENT.checkExists().forPath(nodePath) == null) {
                CLIENT.create().creatingParentsIfNeeded().forPath(nodePath, "node".getBytes());
                log.debug("[GgFramework] create zk node : path={}", nodePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
