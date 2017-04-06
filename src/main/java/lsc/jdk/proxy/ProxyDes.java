package lsc.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉动态代理类描述 动态代理类必须实现InvocationHandler
 *
 * @author shichang.liu
 * @date 2017年3月14日下午5:18:55
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ProxyDes implements InvocationHandler {

    /**
     * proxy 指代我们所代理的那个真实对象 method 指代的是我们所要调用真实对象的某个方法的Method对象 args 指代的是调用真实对象某个方法时接受的参数
     * 
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return null;
    }

    void proxy() {
        /*
         * loader:一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
         * interfaces:
         *      一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，
         *      那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
         * h:    一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
         */
        Proxy.newProxyInstance(ProxyDes.class.getClassLoader(), new Class<?>[] { ProInterface.class }, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // TODO Auto-generated method stub
                return null;
            }
        });
    }
}
