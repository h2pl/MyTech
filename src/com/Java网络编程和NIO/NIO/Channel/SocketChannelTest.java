package com.Java网络编程和NIO.NIO.Channel;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class SocketChannelTest {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(1234));
            SocketChannel socketChannel1 = serverSocketChannel.accept();
            socketChannel1.finishConnect();
            socketChannel1.socket();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
