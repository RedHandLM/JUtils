package soa.tcp;

import java.net.InetSocketAddress;

import soa.tcp.impl.ServiceCenter;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉测试RPC框架
 *
 * @author shichang.liu
 * @date 2017年3月14日下午4:02:06
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TestRPC {
    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Server serviceServer = new ServiceCenter(8088);
                serviceServer.register(SayHelloService.class, SayHelloServiceImpl.class);// 注册服务
                serviceServer.start();
            }
        }).start();
        SayHelloService service = RPCClient.getRemoteProxyObj(SayHelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHello("Hello"));
    }
}
