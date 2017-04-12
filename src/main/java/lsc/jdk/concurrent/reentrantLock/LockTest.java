package lsc.jdk.concurrent.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        Depot depot = new Depot();
        Producer mPro = new Producer(depot);
        Customer mCus = new Customer(depot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);
    }
}
    /*
     *  仓库出现负数
     *  Thread-0 produce (60) --> size=60
        Thread-1 produce (120) --> size=180
        Thread-2 consume (90) <-- size=90
        Thread-3 consume (150) <-- size=-60
        Thread-4 produce (110) --> size=50
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */







// 仓库
class Depot {
    private int size;
    private Lock lock;// 独占锁

    public Depot() {
        this.size = 0;
        this.lock = new ReentrantLock();
    }

    public void produce(int val) {
        lock.lock();
        try {
            size += val;
            System.out.printf("%s produce (%d) --> size=%d\n", Thread.currentThread().getName(), val, size);
        } finally {
            lock.unlock();
        }
    }

    public void consume(int val) {
        lock.lock();
        try {
            size -= val;
            System.out.printf("%s consume (%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
        } finally {
            lock.unlock();
        }
    }
}

// 生产者
class Producer {
    private Depot depot;

    public Producer(Depot depot) {
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
class Customer {
    private Depot depot;

    public Customer(Depot depot) {
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
