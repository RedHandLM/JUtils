package lsc.disruptor.simple_demo;

import com.lmax.disruptor.EventHandler;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉消费者===事件处理器
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {

        System.out.println(event.getEvent());
    }

}
