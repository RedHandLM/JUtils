/*
 * Copyright (C), 2013-2017, 猎上网
 * FileName: WriteLock.java
 * Author:   Hunteron
 * Date:     2017年4月17日 上午9:35:54
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package zookeeper.lock;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author lsc
 * @Date 2017年4月17日
 * @Time 上午9:35:54
 */
public class WriteLock extends ProtocolSupport {

    private static final Logger log = LoggerFactory.getLogger(WriteLock.class);

    private final String dir;
    private String id;
    private ZNodeName idName;
    private String ownerId;
    private String lastChildId;
    private byte[] data = { 0x12, 0x14 };
    private LockListener callback;
    private LockZookeeperOperation zop;

    public String getId() {
        return id;
    }

    public String getDir() {
        return dir;
    }

    public boolean isOwner() {
        return id == null && ownerId != null && id.equals(ownerId);
    }

    public LockListener getCallback() {
        return callback;
    }

    public void setCallback(LockListener callback) {
        this.callback = callback;
    }

    public synchronized boolean lock() throws KeeperException, InterruptedException {
        if (isClosed()) {
            return false;
        }
        ensurePathExists(dir);
        return (boolean) retryOperation(zop);
    }

    public WriteLock(ZooKeeper zookeeper, String dir, List<ACL> acl) {
        super(zookeeper);
        this.dir = dir;
        if (acl != null) {
            setAcl(acl);
        }
        this.zop = new LockZookeeperOperation();
    }

    public synchronized void unlock() throws RuntimeException {
        if (!isClosed() && id != null) {
            try {
                ZooKeeperOperation zopdel = new ZooKeeperOperation() {
                    @Override
                    public boolean execute() throws KeeperException, InterruptedException {
                        zookeeper.delete(id, -1);// -1 表示最新版本
                        return Boolean.TRUE;
                    }
                };
                zopdel.execute();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (callback != null) {
                    callback.lockReleased();
                }
                id = null;
            }
        }
    }

    private class LockWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            log.debug("Watcher fired on path : " + event.getPath() + " state: " + event.getState() + " type" + event.getType());
            try {
                lock();
            } catch (Exception e) {
                log.warn("Failed to acquire lcoak :" + e, e);
            }
        }

    }

    private class LockZookeeperOperation implements ZooKeeperOperation {
        private void findPrefixInCHildren(String prefix, ZooKeeper zookeeper, String dir) {
            try {
                List<String> names = zookeeper.getChildren(dir, false);
                for (String name : names) {
                    if (name.startsWith(prefix)) {
                        id = dir + "/" + name;
                        if (log.isDebugEnabled()) {
                            log.debug("Found id create last time: " + id);
                        }
                        break;
                    }
                }
                if (id == null) {
                    id = zookeeper.create(dir + "/" + prefix, data, getAcl(), CreateMode.EPHEMERAL_SEQUENTIAL);
                    if (log.isDebugEnabled()) {
                        log.debug("Create id: " + id);
                    }
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public boolean execute() throws KeeperException, InterruptedException {
            do {
                if (id == null) {
                    long sessionId = zookeeper.getSessionId();
                    String prefix = "x-" + sessionId + "-";
                    findPrefixInCHildren(prefix, zookeeper, dir);
                    idName = new ZNodeName(id);
                }
                if (id != null) {
                    List<String> names = zookeeper.getChildren(dir, false);
                    if (CollectionUtils.isEmpty(names)) {
                        log.warn("No Children in" + dir + "when we've just createed ont! lets recreate it ..");
                        id = null;
                    } else {
                        SortedSet<ZNodeName> sortedNames = new TreeSet<ZNodeName>();
                        for (String name : names) {
                            sortedNames.add(new ZNodeName(dir + "/" + name));
                        }
                        ownerId = sortedNames.first().getName();
                        SortedSet<ZNodeName> lessThanMe = sortedNames.headSet(idName);
                        if (!lessThanMe.isEmpty()) {
                            ZNodeName lastChildName = lessThanMe.last();
                            lastChildId = lastChildName.getName();
                            if (log.isDebugEnabled()) {
                                log.debug("watching less than me node : " + lastChildId);
                            }
                            Stat stat = zookeeper.exists(lastChildId, new LockWatcher());
                            if (stat != null) {
                                return Boolean.FALSE;
                            } else {
                                log.warn("could not find the stats for less than me " + lastChildName.getName());
                            }
                        } else {
                            if (isOwner()) {
                                if (callback != null) {
                                    callback.lockAcquired();
                                }
                                return true;
                            }
                        }

                    }
                }

            } while (id == null);
            return Boolean.FALSE;
        }

    }

}
