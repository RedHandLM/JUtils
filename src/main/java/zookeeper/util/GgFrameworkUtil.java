package zookeeper.util;

import lsc.clent.GgFrameworkClient;
import lsc.core.GgTable;
import lsc.security.SSDBCoderUtil;
import lsc.util.StringTools;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GgFrameworkUtil {

    private static Logger log = LoggerFactory.getLogger(GgFrameworkUtil.class);

    // 标记client是否已经初始化
    public static Boolean isInit = false;

    // 数据广播节点
    public static final String broadcaset_node = "/broadcaset_node";
    // 订阅监听节点
    public static final String notify_node = "/notify_node";
    // 注册zk地址，命名前缀解析
    private static final String PREFIX = "://";
    // zk连接客户端，curator框架
    private static CuratorFramework CLIENT = null;
    public static final String FRAMEWORK_PREFIX = "GgFramework";

    /**
     * 初始化zookeeper连接，根据【resource.properties】配置的【ggframework.zookeeper】地址 <br>
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void initCuratorFramework() {
        if (CLIENT == null) {
            CLIENT = init();
        }
    }

    /**
     * 根据指定zookeeper服务地址，初始化zookeeper连接 <br>
     *
     * @param zkHost
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void initCuratorFramework(String zkHost) {
        if (CLIENT == null) {
            CLIENT = init(zkHost);
        }
    }

    private static String cleanZkHost(String host) {
        int index = -1;
        if (StringUtils.isNotBlank(host) && (index = host.indexOf(PREFIX)) >= 0) {
            return host.substring(index + PREFIX.length());
        }
        return host;
    }

    private static CuratorFramework init(String zookeeperHost) {
        final String dubbo_registry = cleanZkHost(zookeeperHost);
        CuratorFramework client = CuratorFrameworkUtil.getInstance(dubbo_registry);

        // 添加客户端监听
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState state) {
                if (state.isConnected()) {
                    log.debug("[zookeeperHost] connected to zookeeper : {}", dubbo_registry);
                    isInit = true;
                    GgFrameworkClient.addListenerFromCache();
                }
            }
        });

        return client;
    }

    private static CuratorFramework init() {
        // 默认初始化，读取配置
        String dubbo_registry = PropertieUtil.getStrProp("resource", "ggframework.zookeeper");
        return init(dubbo_registry);
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

    private static void checkZkTableForDbAndTable(String method, GgTable table) throws IllegalArgumentException {
        if (!isZkTableDbAndTableValid(table)) {
            throw new IllegalArgumentException("使用【" + method + "】时必须设置ZkTable的db和table");
        }
    }

    private static void checkZkTableForFieldAndValue(String method, GgTable table) throws IllegalArgumentException {
        if (!isZkTableFieldAndValueValid(table)) {
            throw new IllegalArgumentException("使用【" + method + "】时必须设置ZkTable的field和value");
        }
    }

    // 数据修改广播，广播的节点是：db/table
    public static void broadcastNodeDataChanged(GgTable table) throws Exception {
        checkZkTableForDbAndTable("org.common4s.zookeeper.util.CuratorUtil.broadcastNodeDataChanged(ZkTable)", table);

        String path = broadcaset_node + "/" + table.getDb() + "/" + table.getTable();
        createIfNotExists(path);

        getClient().setData().forPath(path, SSDBCoderUtil.encode(table));
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

    /**
     * 订阅数据变更的监听节点创建 1、先向【订阅监听节点】增加一个子节点，表示自己将订阅指定db、指定table且指定field=value的数据 2、同时在【数据广播节点】下增加一个【数据节点】的监听对象
     * 
     * @param table
     * @return
     * @throws Exception
     */
    public static NodeCache subscribeDataNode(GgTable table) throws Exception {

        checkZkTableForDbAndTable("org.common4s.zookeeper.util.CuratorUtil.subscribeDataNode(ZkTable)", table);
        checkZkTableForFieldAndValue("org.common4s.zookeeper.util.CuratorUtil.subscribeDataNode(ZkTable)", table);

        String temp = "/" + table.getDb() + "/" + table.getTable();
        String path = notify_node + temp;

        // 发布到订阅节点，增加一个对应的子节点；在${notify_node}上增加一个子节点，表示订阅
        createIfNotExists(path);
        getClient().setData().forPath(path, SSDBCoderUtil.encode(table));

        // 同时返回指定节点的事件监听对象
        String broadCastPath = broadcaset_node + temp + "/field:" + table.getField() + ";value:" + table.getValue();
        // 创建对应节点的广播监听
        createIfNotExists(broadCastPath);
        return new NodeCache(GgFrameworkUtil.getClient(), broadCastPath, false);
    }

    // 数据订阅节点的监听，监听节点：${notify_node}
    public static PathChildrenCache subscribeDataNodeNotify() {
        String path = notify_node; // 父节点
        createIfNotExists(path);
        return new PathChildrenCache(getClient(), path, true);
    }

    // 针对订阅节点的数据广播，广播的节点是：db/table/field:${field};value:${value}
    public static void broadcastNodeDataChangedForSubscribe(GgTable table) throws Exception {

        checkZkTableForDbAndTable("org.common4s.zookeeper.util.CuratorUtil.broadcastNodeDataChangedForSubscribe(ZkTable)", table);
        checkZkTableForFieldAndValue("org.common4s.zookeeper.util.CuratorUtil.broadcastNodeDataChangedForSubscribe(ZkTable)", table);

        String path = broadcaset_node + "/" + table.getDb() + "/" + table.getTable() + "/field:" + table.getField() + ";value:" + table.getValue();
        createIfNotExists(path);
        getClient().setData().forPath(path, SSDBCoderUtil.encode(table));
    }
}
