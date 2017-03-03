package lsc.netty.sim_demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        System.out.println("报告");
        System.out.println("信息:右移客户端连接到本客户端");
        System.out.println("ip:"+ch.localAddress().getHostName());
        System.out.println("Port"+ch.localAddress().getPort());
        System.out.println("报告完毕");
        //半包处理 基于换行符
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new StringEncoder());
        ch.pipeline().addLast(new MyServerHanler());
    }

}
