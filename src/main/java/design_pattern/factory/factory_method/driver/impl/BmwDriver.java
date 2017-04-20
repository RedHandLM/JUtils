package design_pattern.factory.factory_method.driver.impl;

import design_pattern.factory.factory_method.driver.Driver;
import design_pattern.factory.simple_factory.Bmw;
import design_pattern.factory.simple_factory.Car;

/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉抽象工厂角色
 *
 * @author shichang.liu
 * @date 2017年4月20日下午6:07:05
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BmwDriver implements Driver {

    @Override
    public Car driverCar() {
        return new Bmw();
    }

}
