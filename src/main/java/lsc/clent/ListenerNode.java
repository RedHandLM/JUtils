package lsc.clent;

import java.util.concurrent.ExecutorService;

import lsc.core.GgTable;
import lsc.core.IChangedService;

public class ListenerNode {

    private GgTable ggTable;
    // 节点数据变更，触发服务
    private IChangedService service;
    private ExecutorService pool;

    public ListenerNode(GgTable ggTable, IChangedService service, ExecutorService pool) {
        super();
        this.ggTable = ggTable;
        this.service = service;
        this.pool = pool;
    }

    public GgTable getGgTable() {
        return ggTable;
    }

    public void setGgTable(GgTable ggTable) {
        this.ggTable = ggTable;
    }

    public IChangedService getService() {
        return service;
    }

    public void setService(IChangedService service) {
        this.service = service;
    }

    public ExecutorService getPool() {
        return pool;
    }

    public void setPool(ExecutorService pool) {
        this.pool = pool;
    }
}