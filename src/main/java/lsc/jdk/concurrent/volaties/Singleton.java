package lsc.jdk.concurrent.volaties;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉并发情况下 双重检查 单例模式
 *
 * @author shichang.liu
 * @date 2017年4月7日上午9:20:08
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Singleton {
    
    private static volatile Singleton singleton = null;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
