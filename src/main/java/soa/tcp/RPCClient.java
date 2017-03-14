package soa.tcp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉RPC代理
 *
 * @author shichang.liu
 * @date 2017年3月14日下午3:50:53
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RPCClient<T> {
    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉将本地的接口调用转换成JDK的动态代理，在动态代理中实现远程接口的调用
     *
     * 2017年3月14日下午3:50:16
     * 
     * @author shichang.liu
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(final Class<?> serviceInterface, final InetSocketAddress adder) {
        return (T) Proxy.newProxyInstance(
                serviceInterface.getClassLoader(), 
                new Class<?>[] { serviceInterface },
               new InvocationHandler() {
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                    Socket socket = null;
                                    ObjectOutputStream output = null;
                                    ObjectInputStream input = null;
                                    try {
                                        socket = new Socket();
                                        socket.connect(adder);
                                        // 将远程服务调用需要的接口类 方法名 参数列表等编码后发送给服务提供者
                                        output = new ObjectOutputStream(socket.getOutputStream());
                                        output.writeUTF(serviceInterface.getName());
                                        output.writeUTF(method.getName());
                                        output.writeObject(method.getParameterTypes());
                                        output.writeObject(args);
                                        // 同步阻塞等待服务器返回结果
                                        input = new ObjectInputStream(socket.getInputStream());
                                        return input.readObject();
                                    } finally {
                                        if (socket != null)
                                            socket.close();
                                        if (output != null)
                                            output.close();
                                        if (input != null)
                                            input.close();
                                    }
                                }
                            });

                        }
                    }
