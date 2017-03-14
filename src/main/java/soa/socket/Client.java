package soa.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉服务器队列
 *
 * @author shichang.liu
 * @date 2017年3月14日下午2:31:27
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        final int length = 100;
        String host = "localhost";
        int port = 5201;
        Socket[] socket = new Socket[length];
        for (int i = 0; i < length; i++) {
            socket[i] = new Socket(host, port);
            System.out.println("第" + i + "次连接成功");
        }
        Thread.sleep(3000);
        for (int i = 0; i < length; i++) {
            socket[i].close();
        }
    }
}
