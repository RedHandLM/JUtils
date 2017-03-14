package soa.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉socket通讯服务端
 *
 * @author shichang.liu
 * @date 2017年3月14日下午1:46:52
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SocketServer {

    public static void main(String[] args) {
        ServerSocket server = null;
        BufferedReader in = null;
        Socket socket = null;
        try {
            // 记录客户端数量
            int count = 0;
            server = new ServerSocket(5209);
            while (true) {
                socket = server.accept();// 调用accept方法开始监听 等待客户端连接
                // 创建一个新的线程
                ServerThread serverThread = new ServerThread(socket);
                // 启动线程
                serverThread.start();
                count++;
                System.out.println("客户端数量" + count);
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的ip是" + address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
