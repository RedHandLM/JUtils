package lsc.jdk.concurrent.aqs;


public class AbstractQueuedSynchronizerSu{
    /*
     * 同步器内部基于一个FIFO队列 对于锁的获取，请求形成节点，将其挂载在队列尾部，而锁资源的释放和获取是从头部开始的向后进行的对于状态state，多个线程的获取会产生一个链式的结构
     */

    // 同步器拥有的三变量之一 sync队列的头结点
    private volatile transient NodeSu head;

    // 同步器拥有的三变量之一 sync队列的尾部结点
    private volatile transient NodeSu tail;

    // 状态
    private volatile int state;

    
}
