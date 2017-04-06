package lsc.jdk.proxy.demo;

public class RealSubject implements Subject {

    @Override
    public void rent() {
        System.out.println("I want rent my house");

    }

    @Override
    public void hello(String str) {
        System.out.println("I say hello" + str);

    }

}
