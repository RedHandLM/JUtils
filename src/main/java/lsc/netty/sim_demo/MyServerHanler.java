package lsc.netty.sim_demo;

import java.text.SimpleDateFormat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHanler extends SimpleChannelInboundHandler<String> {

    private static final String FORMAT = "yyyy-MM-dd hh:mm:ss";
    private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat(FORMAT);

    /*
     * 活跃通道
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * 不活跃通道
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /*
     * 读通道 msg:枚举类型根据你继承的SimpleChannelInboundHandler<I>设置来的 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
     * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
     */
    @Override
    protected void channelRead0(ChannelHandlerContext arg0, String msg) throws Exception {
        System.out.println(FORMAT_DATE + "收到信息:" + msg);
    }

    /*
     * 通道读取完成 在通道读取完成之后可以在这个方法中通知，对应可以做刷新操作
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();// 刷新操作
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
        System.out.println("异常信息： \r\n" + cause.getMessage());

    }

}
