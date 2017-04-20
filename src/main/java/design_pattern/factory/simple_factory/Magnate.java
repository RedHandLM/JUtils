package design_pattern.factory.simple_factory;

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
     * 客户端免除了直接创建产品对象的责任，而仅仅负责“消费”产品。
     * 从开闭原则（对扩展开放；对修改封闭）上来分析下简单工厂模式。
     * 当暴发户增加了一辆车的时候，只要符合抽象产品制定的合同，
     * 那么只要通知工厂类知道就可以被客户使用了。
     * 所以对产品部分来说，它是符合开闭原则的；
     * 但是工厂部分好像不太理想，因为每增加一辆车，
     * 都要在工厂类中增加相应的业务逻辑或者判断逻辑，这显然是违背开闭原则的。
     * 可想而知对于新产品的加入，工厂类是很被动的。
     * 对于这样的工厂类（在我们的例子中是为司机师傅），我们称它为全能类或者上帝类。
     * 
     * 
     *   我们举的例子是最简单的情况，而在实际应用中，很可能产品是一个多层次的树状结构。
     *   由于简单工厂模式中只有一个工厂类来对应这些产品，所以这可能会把我们的上帝累坏了，
     *   也累坏了我们这些程序员:(   于是工厂方法模式作为救世主出现了。 
     * 
     * 
     * 
     * 
     */
    
    public static void main(String[] args) {
        // 告诉司机今天开奔驰
        try {
            Car car = Driver.driverCar("Benz");
            car.drive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
