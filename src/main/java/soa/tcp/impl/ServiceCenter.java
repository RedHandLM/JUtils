package soa.tcp.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import soa.tcp.Server;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉服务中心实现类
 *
 * @author shichang.liu
 * @date 2017年3月14日下午3:26:52
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ServiceCenter implements Server {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final HashMap<String, Class<?>> serviceRegistry = new HashMap<String, Class<?>>();

    private static boolean isRunning = false;

    private static int port;

    public ServiceCenter(int port) {
        this.port = port;
    }

    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();

    }

    @Override
    public void start() {
        try {
            @SuppressWarnings("resource")
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress(port));
            System.out.println("start server.............................");
            while (true) {
                // 监听socket连接 然后由线程池执行
                executor.execute(new ServiceTask(server.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void register(Class<?> serviceInterface, Class<?> impl) {
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPort() {
        return port;
    }

    /**
     * 
     * 〈一句话功能简述〉<br>
     * 〈功能详细描述〉线程类 通过java反射获得远端方法执行结果
     *
     * @author shichang.liu
     * @date 2017年3月14日下午3:37:16
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     */
    private static class ServiceTask implements Runnable {
        Socket clent = null;

        public ServiceTask(Socket clent) {
            this.clent = clent;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try {
                //使用inputStream读取客户端请求
                input = new ObjectInputStream(clent.getInputStream());
                String interfaceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Class<?> serviceClass = serviceRegistry.get(interfaceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(interfaceName + "Not Found");
                }
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);
                output = new ObjectOutputStream(clent.getOutputStream());
                output.writeObject(result);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
    }
}