package lsc.jdk.concurrent.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 实现一个排他锁 一次只能有一个线程可以获得锁
 * 
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
class Mutex implements Lock, java.io.Serializable {

    /**
     */
    private static final long serialVersionUID = 1L;

    // 内部类 自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {

        // 是否处于占用状态 //isHeldExclusively 独家占用
        protected boolean isHeldExclusively() {
            return getState() == 1; // AQS方法
        }

        // 当状态为0的时候获取锁
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // otherWise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁 将状态置为0
        protected boolean tryRelease(int releases) {
            assert releases == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // 将操作代理到Sync上

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);

    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public boolean tryLock(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, paramTimeUnit.toNanos(1l));
    }

    public void unlock() {
        sync.release(1);

    }

    public Condition newCondition() {
        return sync.newCondition();
    }

}
