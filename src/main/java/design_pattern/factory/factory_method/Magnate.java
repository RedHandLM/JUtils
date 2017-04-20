package design_pattern.factory.factory_method;

import design_pattern.factory.factory_method.driver.impl.BenzDriver;
import design_pattern.factory.simple_factory.Car;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉使用者
 *
 * @author shichang.liu
 * @date 2017年4月20日下午5:54:54
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Magnate {

    /*
     * 工厂方法的加入，使得对象的数量成倍增长。当产品种类非常多时，
     * 会出现大量的与之对应的工厂对象，这不是我们所希望的。
     * 因为如果不能避免这种情况，可以考虑使用简单工厂模式与工厂方法模式相结合的方式来减少工厂类：
     *    即对于产品树上类似的种类（一般是树的叶子中互为兄弟的）使用简单工厂模式来实现。
     */
    
    /*
     * 
     * 工厂方法模式仿佛已经很完美的对对象的创建进行了包装，使得客户程序中仅仅处理抽象产品角色提供的接口。
     * 那我们是否一定要在代码中遍布工厂呢？大可不必。也许在下面情况下你可以考虑使用工厂方法模式：
        1)        当客户程序不需要知道要使用对象的创建过程。
        2)        客户程序使用的对象存在变动的可能，或者根本就不知道使用哪一个具体的对象。
                    简单工厂模式与工厂方法模式真正的避免了代码的改动了？
                    没有。
                    在简单工厂模式中，新产品的加入要修改工厂角色中的判断语句；
                    而在工厂方法模式中，要么将判断逻辑留在抽象工厂角色中，要么在客户程序中将具体工厂角色写死（就象上面的例子一样）。
                    而且产品对象创建条件的改变必然会引起工厂角色的修改。
                    面对这种情况，Java的反射机制与配置文件的巧妙结合突破了限制——这在spring中完美的体现了出来。
     */
    
    public static void main(String[] args) {
        try {
            BenzDriver driver = new BenzDriver();
            Car car = driver.driverCar();
            car.drive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
