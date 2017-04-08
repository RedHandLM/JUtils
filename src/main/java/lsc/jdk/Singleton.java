package lsc.jdk;

/**
 * 双重检查  单例模式
 * @author richerd
 *
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
