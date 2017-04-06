package lsc.jdk.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        //我们要代理的真实类
        Subject realSubject =new RealSubject();
        //我们要代理那个真实对象  就将该对象传递进去，最后通过真实对象调用其方法
        InvocationHandler handler=new DynamicProxy(realSubject);
        /*
         * 通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，
         * 也不是我们定义的那组接口的类型，而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，
         * 以$开头，proxy为中，最后一个数字表示对象的标号。
         */
        Subject subject=(Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);
        System.out.println("**********************"+subject.getClass().getName());
        System.out.println("=================================================");
        subject.rent();
        subject.hello("word");
    }
}
