package com.Java网络编程和NIO.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by 周杰伦 on 2018/5/27.
 */
public class 服务端 {
}
class EchoServer {
    private final int port;
    public EchoServer(int port) {
        this.port = port;
    }
    public void start() throws Exception {
        final EchoServerInboundHandler serverHandler = new EchoServerInboundHandler();
        EventLoopGroup group = new NioEventLoopGroup(); // 传输类型使用NIO
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group) // 配置EventLoopGroup
                    .channel(NioServerSocketChannel.class) // 配置Channel的类型
                    .localAddress(new InetSocketAddress(port)) // 配置端口号
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 实现一个ChannelInitializer，它可以方便地添加多个ChannelHandler
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            // i绑定地址，同步等待它完成
            ChannelFuture f = b.bind().sync();
            // 关闭这个Future
            f.channel().closeFuture().sync();
        } finally {
            // 关闭应用程序，一般来说Netty应用只需要调用这个方法就够了
            group.shutdownGracefully().sync();
        }
    }
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.printf(
                    "Usage: %s <port> \n",
                    EchoServer.class.getSimpleName()
            );
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }
}