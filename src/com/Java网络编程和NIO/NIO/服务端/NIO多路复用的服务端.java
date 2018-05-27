package com.Java网络编程和NIO.NIO.服务端;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class NIO多路复用的服务端 {

}
class NioNonBlockingHttpServer {

    private static Selector selector;
    private Charset charset = Charset.forName("utf8");

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

        NioNonBlockingHttpServer httpServer = new NioNonBlockingHttpServer();
        httpServer.select();

    }

    public void request(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setSoTimeout(5000);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8383));
//        serverSocketChannel.register(selector,
//                SelectionKey.OP_CONNECT
//                        | SelectionKey.OP_READ
//                        | SelectionKey.OP_WRITE);
    }

    public void select() throws IOException {
        while (selector.select(500) > 0) {
            Set keys = selector.selectedKeys();

            Iterator it = keys.iterator();

            while (it.hasNext()) {

                SelectionKey key = (SelectionKey) it.next();
                it.remove();

                if (key.isAcceptable()) {
                    accept(key);
                } else if (key.isWritable()) {
                    write(key);
                } else if (key.isReadable()) {
                    receive(key);
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        SocketChannel socketChannel;
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        socketChannel = channel.accept();//接受连接请求
        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        InetSocketAddress local = (InetSocketAddress) channel.socket().getLocalSocketAddress();
        String host = local.getHostName();
        int port = local.getPort();
        System.out.println(String.format("请求地址: %s:%s 接收成功!", host, port));


    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        InetSocketAddress local = (InetSocketAddress) channel.socket().getRemoteSocketAddress();
        String host = local.getHostName();
        String msg = "hello Client";
        channel.write(charset.encode(msg));

        System.out.println(msg);
        key.interestOps(SelectionKey.OP_READ);
    }

    private void receive(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        String receiveData = charset.decode(buffer).toString();

        if ("".equals(receiveData)) {
            key.cancel();
            channel.close();
            return;
        }

        System.out.println(receiveData);
    }
}
