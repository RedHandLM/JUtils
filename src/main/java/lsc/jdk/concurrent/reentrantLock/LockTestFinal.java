package lsc.jdk.concurrent.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTestFinal {
    public static void main(String[] args) {
        Depot1 depot = new Depot1(100);
        Producer1 mPro = new Producer1(depot);
        Customer1 mCus = new Customer1(depot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}

// 仓库
class Depot1 {
    private int capacity; // 仓库的容量
    private int size; // 仓库的实际数量
    private Lock lock;// 独占锁
    private Condition fullCondition; // 生产条件
    private Condition emptyCondtion; // 消费条件

    public Depot1(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.fullCondition = lock.newCondition();
        this.emptyCondtion = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            // left 表示想要生产的数量 有可能数量太多
            int left = val;
            while (left > 0) {
                // 库存已满时 等待消费者消费产品
                while (size >= capacity) {
                    fullCondition.await();
                }
                // 获取实际生产的数量（即库存中新增的数量）
                // 如果 库存+想要生产的数量>总容量 则 实际增量=总容量-当前数量
                int inc = (size + left) > capacity ? (capacity - size) : left;
                size += inc;
                left -= inc;
                System.out.printf("%s produce (%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
                // 通知消费者可以消费了
                emptyCondtion.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            int left = val;
            while (left > 0) {
                // 库存为0时等待生产者生产产品
                while (size <= 0) {
                    emptyCondtion.await();
                }
                // 获取实际消费的数量 即库存中减少的数量
                // 库存<客户想要消费的数量 则实际消费量=库存
                // 否则 实际消费量等于 客户想要消费的数量
                int dec = (size < left) ? size : left;
                size -= dec;
                left -= dec;
                System.out.printf("%s consume (%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
                // 通知生产者进行生产
                fullCondition.signal();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String toString() {
        return "capacity:  " + capacity + ", actual size: " + size;
    }
}

// 生产者
class Producer1 {
    private Depot1 depot;

    public Producer1(Depot1 depot) {
        this.depot = depot;
    }

    // 消费产品 新建一个线程向仓库总生产产品
    public void produce(final int val) {
        new Thread() {
            @Override
            public void run() {
                depot.produce(val);
            }
        }.start();
    }
}

// 消费者
class Customer1 {
    private Depot1 depot;

    public Customer1(Depot1 depot) {
        this.depot = depot;
    }

    // 消费产品 新建一个线程从仓库中消费产品
    public void consume(final int val) {
        new Thread() {
            @Override
            public void run() {
                depot.consume(val);
            }
        }.start();
    }

}
