package design_pattern.factory.abstract_factory.abstract_factory;

import design_pattern.factory.abstract_factory.conditioner.Air_conditioner;
import design_pattern.factory.abstract_factory.engine.Engine;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 抽象工厂接口
 *
 * @author shichang.liu
 * @date 2017年4月20日下午6:25:54
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface Abstract_CarFactory {
    
    public Air_conditioner install_air_conditioner();

    public Engine install_air_engine();

}
