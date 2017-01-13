package lsc.disruptor.simple_demo;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 onData用来发布事件，每调用一次就发布一个事件 它的参数会通过事件传递给消费者
     * 
     * @param bb
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void onData(ByteBuffer b) {

        // ringBuffer 可以看做是一个事件序列 next就是取下一个事件的索引
        long sequence = ringBuffer.next();
        try {
            // 用上面的索引取出一个公的事件用于填充
            LongEvent event = ringBuffer.get(sequence);

            event.setEvent(b.getLong(0));
        } finally {
            // 发布事件
            ringBuffer.publish(sequence);
        }
    }

}
