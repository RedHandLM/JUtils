package lsc.jdk.concurrent.AQS;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 Volatile 关键字
 * 
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class VolatileSu {

    /*
     * 可见性。对一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。 
     * 原子性：对任意单个volatile变量的读/写具有原子性，但类似于volatile++这种复合操作不具有原子性。
     * 内存语义： 当读一个volatile变量时，JVM会把该线程对应的本地内存置为无效。线程接下来将从主内存中读取共享变量。
     *        当写一个volatile变量时，JVM会把该线程对应的本地内存中的共享变量刷新到主内存
     *        
     *        内存读写语义总结：
     *          线程A写一个volatile变量，实质上是线程A向接下来将要读这个volatile变量的某个线程发出了（其对共享变量所在修改的）消息。
     *          线程B读一个volatile变量，实质上是线程B接收了之前某个线程发出的（在写这个volatile变量之前对共享变量所做修改的）消息。
     *          线程A写一个volatile变量，随后线程B读这个volatile变量，这个过程实质上是线程A通过主内存向线程B发送消息。
     *          
     *        JVM实现内存volatile，编译器在生成字节码时，会在指令序列中插入内存屏障来禁止特定类型的处理器重排序
     *          在每个volatile写操作的前面插入一个StoreStore屏障。
                                                在每个volatile写操作的后面插入一个StoreLoad屏障。
                                                在每个volatile读操作的后面插入一个LoadLoad屏障。
                                                在每个volatile读操作的后面插入一个LoadStore屏障。
     */

    volatile long v = 0l;

    // 单个Volatile变量的写操作
    public void set(long v) {
        this.v = v;
    }

    // 复合Volatile变量的读写操作
    public long getAndIncrement() {
        return v++;
    }

    // 单个Volatile变量的读操作
    public long get() {
        return v;
    }

    // 和上面的方法等价
    class VolatileExample {
        long v = 0l;

        // 对单个普通变量的写操作用一个锁来同步
        public synchronized void set(long l) {
            v = l;
        }

        // 对普通变量的复合操作
        public void getAndIncrement() {
            long tmp = get(); // 同步读操作
            tmp += 1l; // 普通写操作
            set(tmp); // 同步写操作
        }

        // 对单个变量的读取操作
        public long get() {
            return v;
        }

    }

    static class VolatileThreadExample {
        static int a = 0;
        volatile static boolean flag = false;

        public static void write() {
            a = 1;
            flag = true;
        }

        public static void reader() {
            if (flag) {
                int i = a;
                System.out.println(i);
            }
        }

        /*
         * 这里A线程写一个volatile变量后，B线程读同一个volatile变量。
         *  A线程在写volatile变量之前所有可见的共享变量，在B线程读同一个volatile变量后，将立即变得对B线程可见。
         */
        public static void main(String[] args) {
            Thread thread1 = new Thread(new Runnable() {
                public void run() {
                    // 1:线程修改共享变量
                    // 2:对Volatile变量做写操作
                    write();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                public void run() {
                    // 3:线程2读取同一个Volatile变量
                    // 4:线程B读取共享变量
                    reader();
                }
            });

            thread1.start();
            thread2.start();

        }

    }

}
