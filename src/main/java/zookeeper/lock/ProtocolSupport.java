/*
 * Copyright (C), 2013-2017, 猎上网
 * FileName: ProtocolSupport.java
 * Author:   Hunteron
 * Date:     2017年4月17日 上午9:37:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package zookeeper.lock;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br>
 * zookeeper操作可能会失败，这个类封装了多次重试
 *
 * @author lsc
 * @Date 2017年4月17日
 * @Time 上午9:37:13
 */
public class ProtocolSupport {

    private static final Logger LOG = LoggerFactory.getLogger(ProtocolSupport.class);

    protected ZooKeeper zookeeper;
    private AtomicBoolean closed = new AtomicBoolean(false);
    private long retryDelay = 500l;
    private int retryCount = 10;
    private List<ACL> acl = ZooDefs.Ids.OPEN_ACL_UNSAFE;

    public ProtocolSupport(ZooKeeper zookeeper) {
        this.zookeeper = zookeeper;
    }

    public ZooKeeper getZookeeper() {
        return zookeeper;
    }

    public void close() {
        if (closed.compareAndSet(false, true)) {
            doClose();
        }
    }

    private void doClose() {

    }

    protected boolean isClosed() {
        return closed.get();
    }

    public AtomicBoolean getClosed() {
        return closed;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public List<ACL> getAcl() {
        return acl;
    }

    public void setAcl(List<ACL> acl) {
        this.acl = acl;
    }

    protected Object retryOperation(ZooKeeperOperation operation) throws KeeperException, InterruptedException {
        KeeperException exception = null;
        for (int i = 0; i < retryCount; i++) {
            try {
                return operation.execute();
            } catch (KeeperException.SessionExpiredException e) {
                LOG.warn("Session expired for: " + zookeeper + "so reconnecting due to:" + e, e);
                throw e;
            } catch (KeeperException.ConnectionLossException e) {
                if (exception == null) {
                    exception = e;
                    LOG.debug("Attempt " + i + "failed with connection loss so attempting to reconnect: " + e, e);
                    retryDalay(i);
                }
            }
        }

        return operation;

    }

    protected void ensurePathExists(String path) {
        exsureExists(path, null, acl, CreateMode.PERSISTENT);
    }

    private void exsureExists(final String path, final byte[] data, final List<ACL> acl, final CreateMode flags) {
        try {
            retryOperation(new ZooKeeperOperation() {
                @Override
                public boolean execute() throws KeeperException, InterruptedException {
                    Stat stat = zookeeper.exists(path, false);
                    if (stat != null) {
                        return true;
                    }
                    zookeeper.create(path, data, acl, flags);
                    return true;
                }
            });
        } catch (KeeperException e) {
            LOG.warn("Caught:  " + e, e);
        } catch (InterruptedException e) {
            LOG.warn("Caught:  " + e, e);
        }
    }

    protected void retryDalay(int attemptCount) {
        if (attemptCount > 0) {
            try {
                Thread.sleep(attemptCount * retryDelay);
            } catch (InterruptedException e) {
                LOG.debug("Failed to sleep: " + e, e);
            }
        }
    }

}
