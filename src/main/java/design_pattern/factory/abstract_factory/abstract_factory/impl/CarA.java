package design_pattern.factory.abstract_factory.abstract_factory.impl;

import design_pattern.factory.abstract_factory.abstract_factory.Abstract_CarFactory;
import design_pattern.factory.abstract_factory.conditioner.Air_conditioner;
import design_pattern.factory.abstract_factory.conditioner.impl.Air_conditioner_Haier;
import design_pattern.factory.abstract_factory.engine.Engine;
import design_pattern.factory.abstract_factory.engine.impl.Engine_Import;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉具体工厂A类
 *
 * @author shichang.liu
 * @date 2017年4月20日下午6:32:04
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CarA implements Abstract_CarFactory {

    @Override
    public Air_conditioner install_air_conditioner() {
        return new Air_conditioner_Haier();
    }

    @Override
    public Engine install_air_engine() {
        
        return new Engine_Import();
    }

}
