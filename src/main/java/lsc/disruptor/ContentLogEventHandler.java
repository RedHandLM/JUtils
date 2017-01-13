package lsc.disruptor;

import lsc.bean.resume.ResumeEvent;

import com.lmax.disruptor.EventHandler;

public class ContentLogEventHandler implements EventHandler<ResumeEvent> {

    public void onEvent(ResumeEvent event, long sequence, boolean endOfBatch) throws Exception {
        
        

    }

}
