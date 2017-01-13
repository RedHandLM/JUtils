package lsc.disruptor.performanceCompare;

import lsc.disruptor.performanceCompare.bean.LogEvent;

import com.lmax.disruptor.EventHandler;

public class LogEventConsumer implements EventHandler<LogEvent> {
    private long startTime;
    private int i;

    public LogEventConsumer() {
        this.startTime = System.currentTimeMillis();
    }

    public void onEvent(LogEvent logEvent, long seq, boolean bool) throws Exception {
        i++;
        if (i == LinkedBlockingQueueTest.eventNum) {
            long endTime = System.currentTimeMillis();
            System.out.println(" costTime = " + (endTime - startTime) + "ms");
        }
    }
}