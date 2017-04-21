package lsc.clent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import lsc.core.GgTable;
import lsc.core.IChangedService;
import lsc.security.SSDBCoderUtil;
import lsc.util.GgFrameworkUtil;

import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GgFrameworkClient {

    private static Logger log = LoggerFactory.getLogger(GgFrameworkClient.class);
    
	public static final Map<GgTable, NodeCache> nodeCacheMap = new HashMap<GgTable, NodeCache>();
	
	private static final List<ListenerNode> LISTENER_NODES = new ArrayList<ListenerNode>(10);
	
	/**
	 * 监听数据更新
	 */
	public static void listener(GgTable ggTable, IChangedService service, ExecutorService pool) throws Exception {
	    if(GgFrameworkUtil.isInit) {
            addListener(ggTable, service, pool);
	    } else {
	        LISTENER_NODES.add(new ListenerNode(ggTable, service, pool));
	    }
	}
	
	public static void  addListenerFromCache() {
	    if(LISTENER_NODES.size() > 0) {
            log.debug("[{}] add listener from node cache", GgFrameworkUtil.FRAMEWORK_PREFIX);
	        for(ListenerNode node : LISTENER_NODES) {
	            try {
                    addListener(node);
                } catch (Exception e) {
                    e.printStackTrace();
                }
	        }
	        LISTENER_NODES.clear();
	    }
	}
	

    private static void addListener(ListenerNode listenerNode) throws Exception {
        addListener(listenerNode.getGgTable(), listenerNode.getService(), listenerNode.getPool());
    }
	
	private static void addListener(GgTable ggTable, IChangedService service, ExecutorService pool) throws Exception {
        log.debug("[{}] add listener ggtable {}", GgFrameworkUtil.FRAMEWORK_PREFIX, ggTable);
        final NodeCache nodeCache = GgFrameworkUtil.broadcastNotifyNodeChanged(ggTable);
        nodeCache.start(true);
        final IChangedService temp = service;
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                GgTable data = SSDBCoderUtil.decode(nodeCache.getCurrentData().getData());
                temp.excute(data);
            }
        }, pool);
        nodeCacheMap.put(ggTable, nodeCache);
	}

	public static void listener(String dbName, String tableName, IChangedService service, ExecutorService pool) throws Exception {
		listener(new GgTable(dbName, tableName), service, pool);
	}
	
	/**
	 * 取消监听
	 */
	public static void releaseListener(GgTable ggTable) throws IOException {
		NodeCache cache = nodeCacheMap.get(ggTable);
		if(cache != null) {
			cache.close();
		}
	}
}