package lsc.jdk.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉使用java中的AtomicStampedReference解决CAS算法产生的ABA问题
 * 参考网址：http://blog.hesey.net/2011/09/resolve-aba-by-atomicstampedreference.html
 * 
 * @author shichang.liu
 * @date 2017年4月20日下午3:55:32
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AtomicStampedReferenceSu {
    /*
     * Stup1:线程2----CAS----->A-C-A 
     * Stup2:线程1----CAS----->A-B 产生ABA问题
     */
    /**
     * 分别用AtomicInteger和AtomicStampedReference来对初始值为100的原子整型变量进行更新，
     * AtomicInteger会成功执行CAS操作，而加上版本戳的AtomicStampedReference对于ABA问题会执行CAS失败：
     */

    // 未加版本戳的CAS
    private static AtomicInteger atomicInt = new AtomicInteger(100);

    // 加版本戳的CAS
    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<Integer>(100, 0);

    public static void main(String[] args) {
        Thread intT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }
        });
        Thread intT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println("atomicInt------------->" + c3);

            }
        });
        intT1.start();
        intT2.start();
        try {
            intT1.join();
            intT2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread refT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int stamp = atomicStampedRef.getStamp();
                atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                atomicStampedRef.compareAndSet(101, 100, stamp, stamp + 1);
            }
        });

        Thread refT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedRef.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println("atomicStampedRef------------->" + c3);
            }
        });
        refT1.start();
        refT2.start();

    }

}
