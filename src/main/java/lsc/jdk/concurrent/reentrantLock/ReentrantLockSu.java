package lsc.jdk.concurrent.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入的互斥锁 又叫：独占锁
 * ReentrantLock锁在同一个时间点只能被一个线程锁持有；
 * 而可重入的意思是，ReentrantLock锁，可以被单个线程多次获取。
    ReentrantLock分为“公平锁”和“非公平锁”。它们的区别体现在获取锁的机制上是否公平。
    “锁”是为了保护竞争资源，防止多个线程同时操作线程而出错，
    ReentrantLock在同一个时间点只能被一个线程获取(当某线程获取到“锁”时，其它线程就必须等待)；
    ReentraantLock是通过一个FIFO（先进先出）的等待队列来管理获取该锁所有线程的。
                在“公平锁”的机制下，线程依次排队获取锁；
                而“非公平锁”在锁是可获取状态时，不管自己是不是在队列的开头都会获取锁。
 * @author richerd
 *
 */
public class ReentrantLockSu {
    // 函数列表
    public static void main(String[] args) {
        try {
            // 创建一个独占锁
            // 参数为true 代表是公平锁 默认为false 是非公平锁
            ReentrantLock lock = new ReentrantLock();
            ReentrantLock reentrantLock = new ReentrantLock(true);
            // 获取当前线程保持此锁的次数
            reentrantLock.getHoldCount();
            // 私有方法 返回目前拥有此锁的线程，如果不被任何线程拥有，返回null
            // reentrantLock.getOwner();

            // 私有方法 返回一个collection<Thread> 包含可能等待此锁的线程
            // reentrantLock.getQueuedThreads();

            // 返回正在等待获取此锁的线程估计数
            reentrantLock.getQueueLength();

            // 私有方法 返回一个collection<Thread> 包含可能正在等待与此锁相关给定条件的那些线程
            // reentrantLock.getWaitingThreads(Condition condition);

            Condition condition = null;
            Thread thread = null;

            // 返回等待与此锁相关的给定条件的线程估计数。
            reentrantLock.getWaitQueueLength(condition);

            // 查询给定线程是否正在等待获取此锁。
            boolean f = reentrantLock.hasQueuedThread(thread);

            // 查询是否有些线程正在等待获取此锁。
            boolean fi = reentrantLock.hasQueuedThreads();

            // 查询是否有些线程正在等待与此锁有关的给定条件。
            boolean re = reentrantLock.hasWaiters(condition);

            // 如果是“公平锁”返回true，否则返回false。
            re = reentrantLock.isFair();

            // 查询当前线程是否保持此锁。
            re = reentrantLock.isHeldByCurrentThread();

            // 查询此锁是否由任意线程保持。
            re = reentrantLock.isLocked();

            // 获取锁。
            reentrantLock.lock();

            // 如果当前线程未被中断，则获取锁。
            reentrantLock.lockInterruptibly();

            // 返回用来与此 Lock 实例一起使用的 Condition 实例。
            condition = reentrantLock.newCondition();

            // 仅在调用时锁未被另一个线程保持的情况下，才获取该锁。
            re = reentrantLock.tryLock();

            // 如果锁在给定等待时间内没有被另一个线程保持，且当前线程未被中断，则获取该锁。
            re = reentrantLock.tryLock(10000l, TimeUnit.SECONDS);

            // 试图释放此锁。
            reentrantLock.unlock();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    
    
    

}
