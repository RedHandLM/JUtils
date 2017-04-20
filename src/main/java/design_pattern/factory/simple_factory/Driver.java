package design_pattern.factory.simple_factory;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉工厂类角色
 *
 * @author shichang.liu
 * @date 2017年4月20日下午5:51:07
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Driver {
    // 工厂方法，返回类型为具体的抽象产品角色
    public static Car driverCar(String s) throws Exception {
        // 具体判断逻辑 返回具体的产品角色给Client
        if (s.equalsIgnoreCase("BenZ")) {
            return new BenZ();
        } else if (s.equalsIgnoreCase("Bmw")) {
            return new Bmw();
        } else {
            throw new Exception();
        }
    }
}
