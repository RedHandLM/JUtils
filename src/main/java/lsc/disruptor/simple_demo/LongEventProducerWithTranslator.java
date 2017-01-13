package lsc.disruptor.simple_demo;

import java.nio.ByteBuffer;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class LongEventProducerWithTranslator {

    // 一个translator 可以看做是一个时间初始化器
    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {

        public void translateTo(LongEvent event, long sequence, ByteBuffer b) {

            event.setEvent(b.getLong(0));
        }
    };

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer b) {
        ringBuffer.publishEvent(TRANSLATOR, b);
    }

}
