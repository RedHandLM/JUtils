package soa.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉服务端
 *
 * @author shichang.liu
 * @date 2017年3月14日下午2:35:23
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Server {
    private int port = 5201;
    private ServerSocket serverSocket = null;

    public Server() throws IOException {
        serverSocket = new ServerSocket(port, 3);
        System.out.println("服务器启动");
    }

    public void service() throws IOException {
        while (true) {
            Socket socket = null;
            socket = serverSocket.accept();
            System.out.println("new connection accept" + socket.getInetAddress() + ":" + socket.getPort());
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        Thread.sleep(60000 * 10);
        server.service();
    }

}
