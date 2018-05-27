package com.Java网络编程和NIO.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by 周杰伦 on 2018/5/27.
 */
class EchoServerInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.printf("Server received: %s \n", in.toString(CharsetUtil.UTF_8));
        // 由于读事件不是一次性就能把完整消息发送过来的，这里并没有调用writeAndFlush
        ctx.write(in); // 直接把消息写回给客户端(会被出站消息处理器处理,不过我们的应用没有实现任何出站消息处理器)
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 等读事件已经完成时,冲刷之前写数据的缓冲区
        // 然后添加了一个监听器，它会在Future完成时进行关闭该Channel.
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    // 处理异常，输出异常信息，然后关闭Channel
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}