package lsc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private volatile static Properties properties = null;
    private static String name = "password";

    /**
     * 双检查锁单例模式
     */
    private static Properties getProperties() {
        String filePath = new File("src/test/java/config.properties").getAbsolutePath();
        File f = new File(filePath);
        FileInputStream fis = null;
        try {
            if (properties == null) {
                synchronized (Config.class) {
                    if (properties == null) { // 二次检查
                        fis = new FileInputStream(f);
                        properties=new Properties();
                        properties.load(fis);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty() {
        return getProperties().getProperty(name);
    }

    public static void main(String[] args) {
        System.out.println("属性=====" + getProperties());
        ;
    }

}
