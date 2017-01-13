package lsc.disruptor.performanceCompare.bean;

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
public class LogEventFactory implements EventFactory<LogEventFactory> {

    public LogEventFactory newInstance() {

        return new LogEventFactory();
    }

}
