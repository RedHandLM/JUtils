package design_pattern.factory.abstract_factory;

import design_pattern.factory.abstract_factory.abstract_factory.Abstract_CarFactory;
import design_pattern.factory.abstract_factory.abstract_factory.impl.CarA;
import design_pattern.factory.abstract_factory.abstract_factory.impl.CarB;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉具体使用
 *
 * @author shichang.liu
 * @date 2017年4月20日下午6:32:56
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class UseMain {
    
    public static void main(String[] args) {
        Abstract_CarFactory factoryA = new CarA();
        factoryA.install_air_conditioner().work();
        factoryA.install_air_engine().work();
        Abstract_CarFactory factoryB = new CarB();
        factoryB.install_air_conditioner().work();
        factoryB.install_air_engine().work();
    }
}
