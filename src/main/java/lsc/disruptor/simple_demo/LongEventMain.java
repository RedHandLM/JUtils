package lsc.disruptor.simple_demo;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class LongEventMain {
    /**
     * CAS操作：Compare And Swap/Set  
     *        Java AtomicLong就是CAS操作
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        
        LongEventFactory factory = new LongEventFactory();
        
        int bufferSize = 1024;      // RingBuffer 大小，必须是 2 的 N 次方；
        
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        
        disruptor.handleEventsWith(new LongEventHandler());
        
        disruptor.start();
        
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        
        ByteBuffer b = ByteBuffer.allocate(8);
        
        for (long l = 0; true; l++) {
            b.putLong(0, l);
            producer.onData(b);
        }
    }
}
