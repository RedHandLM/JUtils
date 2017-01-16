package lsc.jdk.concurrent.syn_util;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 */
public class ConditionSu {
    public static void main(String[] args) {
        final ReentrantLock reentrantLock = new ReentrantLock(); // 重入锁
        /**
         * 两个队列： 一：AQS维护的队列====当前等待资源的队列 AQS会在资源释放后一次唤醒从前到后的所有结点 使他们对应的线程恢复，直到线程为空为止；
         * 二：Condition队列====维护一个等待Signal信号的队列， ============同一个线程只能同时存在以上连个队列中的一个============== 流程：
         * 1：线程1调用reentrantLock.lock时，线程被加入AQS中 2：线程1调用awit方法，该线程从AQS中移除，对应的操作是释放做
         * 3：接着被加入Condition队列中，意味着该线程需要等待signal信号 4：线程2 因为线程1释放锁而被唤醒，并判断可以取得锁，于是获取锁同时被加入AQS队列中 5：线程2调用Condition signal方法
         * 这个时候Condition等待队列中之后一个节点，于是被取出来放入AQS队列中，这个时候线程1还没有被唤醒 6：signal方法执行完毕， 线程2调用reentrantLock.unLock()方法
         * 释放锁，这个时候因为AQS队列中只有一个节点，于是按照从头到尾顺序唤醒了线程1 同时恢复执行
         * 
         */

        /*
         * 多线程中的协调通信的工具类 使得某个，或者某些线程一起等待某个条件（Condition）, 只有当该条件具备( signal 或者 signalAll方法被带调用)时 ，这些等待线程才会被唤醒，从而重新争夺锁。
         * 其实Condition内部维护了等待队列的头结点和尾节点，该队列的作用是存放等待signal信号的线程，该线程被封装为Node节点后存放于此。
         */
        final Condition condition = reentrantLock.newCondition();// 返回的是AbstractQueuedSynchronizer 的Condition实现

        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println("我要等待一个信号" + this);
                    condition.await();
                    System.out.println("等待中。。。。。。。。。。。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我拿到一个信号" + this);
                reentrantLock.unlock();
            }
        }, "waitThread1");

        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                reentrantLock.lock();
                System.out.println("我拿到锁了" + this);
                try {
                    Thread.sleep(3000);
                    System.out.println("睡眠中。。。。。。。。。。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("================>唤醒");
                condition.signalAll();// 唤醒
                System.out.println("我发了一个信号" + this);
                reentrantLock.unlock();
                System.out.println("释放======================>锁");
            }
        }, "singalThread");

        thread1.start();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 Condition.await()的内部实现
     * 
     * @throws InterruptedException
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public final void await() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
//        AbstractQueuedLongSynchronizer.Node localNode = addConditionWaiter(); // 包装当前线程 添加到Candition自己维护的链表中
//        long l = AbstractQueuedLongSynchronizer.this.fullyRelease(localNode); // 释放当前线程占用的锁，从代码看出当前线程是有占用锁
//        int i = 0;
//        do {
            // if (AbstractQueuedLongSynchronizer.this.isOnSyncQueue(localNode))
//            break;
//            LockSupport.park(this);

            // 释放完成后While()遍历AQS的队列，看当前节点是否还在队列中，
            // 不在说明还没有竞争锁的资格，将继续沉睡，直到加入到队列中为止，
            // 其实是在调用singal时加入AQS队列

//        } while ((i = checkInterruptWhileWaiting(localNode)) == 0);
        // 被唤醒之后重新开始正式竞争锁，如果竞争不到还是会重新沉睡，等待重新唤醒开始竞争
//        if ((AbstractQueuedLongSynchronizer.this.acquireQueued(localNode, l)) && (i != -1))
//            i = 1;
//        if (localNode.nextWaiter != null)
//            unlinkCancelledWaiters();
//        if (i == 0)
//            return;
//        reportInterruptAfterWait(i);
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 Condition.signal()的内部实现
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public final void signal() {
        // if (!(AbstractQueuedLongSynchronizer.this.isHeldExclusively())) {
        // throw new IllegalMonitorStateException();
        // }
        // // firstWaiter是condition自己维护的链表的第一个头结点，取出第一个开始唤醒操作
        // AbstractQueuedLongSynchronizer.Node localNode = this.firstWaiter;
        // if (localNode == null)
        // return;
        // doSignal(localNode);// 做唤醒操作
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉唤醒操作
     *
     * @param paramNode
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    // private void doSignal(AbstractQueuedLongSynchronizer.Node paramNode) {
    // do {
    // if ((this.firstWaiter = paramNode.nextWaiter) == null)// 修改旧头结点的修改 并完成旧头结点移除工作
    // this.lastWaiter = null;
    // paramNode.nextWaiter = null;
    // // 将老的头结点加入AQS队列中
    // } while ((!(AbstractQueuedLongSynchronizer.this.transferForSignal(paramNode))) && ((paramNode = this.firstWaiter)
    // != null));
    // }

}
