package soa.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉客户端
 *
 * @author shichang.liu
 * @date 2017年3月14日下午2:03:21
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket("127.0.0.1", 5209);
            System.out.println("客户端启动成功==================");
            // 获取输出流 向服务器端发送信息 并将输出流打包为打印流
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.write("用户名：lsc;密码：aa444");
            pw.flush();
            socket.shutdownOutput();// 关闭输出流
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info;
            while ((info = br.readLine()) != null) {
                System.out.println("我是服务器响应： 我说" + info);
            }
            br.close();
            pw.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("can not listen to:" + e);
        }
    }

}