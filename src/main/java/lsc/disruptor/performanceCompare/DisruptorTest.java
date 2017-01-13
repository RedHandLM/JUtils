package lsc.disruptor.performanceCompare;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lsc.disruptor.performanceCompare.bean.LogEvent;
import lsc.disruptor.performanceCompare.bean.LogEventFactory;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

public class DisruptorTest {
    public static void main(String[] args) {
        LogEventFactory factory = new LogEventFactory();
        int ringBufferSize = 1024*1024;
        Executor executor=Executors.newCachedThreadPool();
        final Disruptor<LogEvent> disruptor = new Disruptor<LogEvent>(factory, ringBufferSize, executor, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE);
        LogEventConsumer consumer = new LogEventConsumer();
        disruptor.handleEventsWith(consumer);
        disruptor.start();
        new Thread(new Runnable() {
            public void run() {
                RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
                for (int i = 0; i < LinkedBlockingQueueTest.eventNum; i++) {
                    long seq = ringBuffer.next();
                    LogEvent logEvent = ringBuffer.get(seq);
                    logEvent.setLogId(i);
                    logEvent.setContent("c" + i);
                    ringBuffer.publish(seq);
                }
            }
        }).start();
    }
}