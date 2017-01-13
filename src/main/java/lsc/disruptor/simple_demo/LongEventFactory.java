package lsc.disruptor.simple_demo;

import com.lmax.disruptor.EventFactory;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉由于需要让Disruptor创建事件，需要声明一个EventFactory来实例化event对象
 *
 * @author Hunteron
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    public LongEvent newInstance() {

        return new LongEvent();
    }

}
