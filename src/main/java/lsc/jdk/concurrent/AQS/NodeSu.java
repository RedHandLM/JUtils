package lsc.jdk.concurrent.AQS;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 AQS维护的队列中就是这样的Node 主要负责保存该节点的线程引用，同步等待队列（以下简称sync队列）的前驱和后继节点，同时也包括了同步状态。
 * 
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class NodeSu {

    static final NodeSu SHARED = new NodeSu();
    static final NodeSu EXCLUSIVE = null;

    // waitStatus=====>表示节点的状态。其中包含的状态有：
    static final int CANCELLED = 1; // 表示当前的线程被取消
    static final int SIGNAL = -1; // 表示当前节点的后继节点包含的线程需要运行，也就是unpark
    static final int CONDITION = -2;// 表示当前节点在等待condition，也就是在condition队列中
    static final int PROPAGATE = -3;// 表示当前场景下后续的acquireShared能够得以执行

    volatile int waitStatus;

    // prev========>前驱节点，比如当前节点被取消，那就需要前驱节点和后继节点来完成连接
    volatile NodeSu prev;

    // ====>后继节点。
    volatile NodeSu next;

    // 入队列时的当前线程。
    volatile Thread thread;

    // 存储condition队列中的后继节点。
    NodeSu nextWaiter;

    final boolean isShared() {
        return (this.nextWaiter == SHARED);
    }

    final NodeSu predecessor() throws NullPointerException {
        NodeSu localNodeSu = this.prev;
        if (localNodeSu == null) {
            throw new NullPointerException();
        }
        return localNodeSu;
    }

    NodeSu() {
    }

    NodeSu(Thread paramThread, NodeSu paramNodeSu) {
        this.nextWaiter = paramNodeSu;
        this.thread = paramThread;
    }

    NodeSu(Thread paramThread, int paramInt) {
        this.waitStatus = paramInt;
        this.thread = paramThread;
    }
}
