package lsc.jdk.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object subject;// 我们要代理的真实对象

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理之前做一些自己的操作：before rent hosuse");
        System.out.println("代理的方法："+method.getName());
        
        method.invoke(subject, args);
        
        System.out.println("代理之后做一些自己的操作：after rent hosuse");
        
        return null;
    }
}
