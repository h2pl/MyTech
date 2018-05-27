package com.Java网络编程和NIO.NIO.客户端;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class 基于线程池的伪异步NIO模型 {
    public static void main(String[] args) {
        基于线程池的伪异步NIO模型 a = new 基于线程池的伪异步NIO模型();
        a.startServer();
    }
    private Charset charset = Charset.forName("utf8");

    class WriteThread implements Runnable {
        private SelectionKey key;
        public WriteThread(SelectionKey key) {
            this.key = key;
        }
        @Override
        public void run() {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            Socket socket = socketChannel.socket();
            try {
                socketChannel.finishConnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetSocketAddress remote = (InetSocketAddress) socketChannel.socket().getRemoteSocketAddress();
            String host = remote.getHostName();
            int port = remote.getPort();
            System.out.println(String.format("访问地址: %s:%s 连接成功!", host, port));

        }
    }
    class ReadThread implements Runnable {
        private SelectionKey key;
        public ReadThread(SelectionKey key) {
            this.key = key;
        }
        @Override
        public void run() {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                socketChannel.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.flip();
            String receiveData = null;
            try {
                receiveData = new String(buffer.array(), "utf8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if ("".equals(receiveData)) {
                key.cancel();
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            System.out.println(receiveData);
        }
    }
    class ConnectThread implements Runnable {
        private SelectionKey key;
        public ConnectThread(SelectionKey key) {
            this.key = key;
        }
        @Override
        public void run() {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = charset.encode("hello world");
            try {
                socketChannel.write(byteBuffer);
                System.out.println("hello world");
            } catch (IOException e) {
                e.printStackTrace();
            }
            key.interestOps(SelectionKey.OP_READ);
        }
    }
    public void startServer() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            SocketChannel socketChannel = SocketChannel.open();
            Selector selector = Selector.open();

            socketChannel.configureBlocking(false);
            InetSocketAddress inetAddress = new InetSocketAddress(1234);

            socketChannel.connect(inetAddress);
            socketChannel.register(selector, SelectionKey.OP_CONNECT |
                    SelectionKey.OP_READ |
                    SelectionKey.OP_WRITE);

            while (selector.select(500) > 0) {
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    if (key.isConnectable()) {
                        executorService.submit(new ConnectThread(key));
                    }else if(key.isReadable()) {
                        executorService.submit(new ReadThread(key));
                    }else {
                        executorService.submit(new WriteThread(key));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
