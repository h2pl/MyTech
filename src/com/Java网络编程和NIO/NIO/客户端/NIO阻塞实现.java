package com.Java网络编程和NIO.NIO.客户端;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class NIO阻塞实现 {
}
class NioBlockingHttpClient {

    private SocketChannel socketChannel;
    private String host;


    public static void main(String[] args) throws IOException {

        for (String host: HttpConstant.HOSTS) {

            NioBlockingHttpClient client = new NioBlockingHttpClient(host, HttpConstant.PORT);
            client.request();

        }

    }

    public NioBlockingHttpClient(String host, int port) throws IOException {
        this.host = host;
        socketChannel = SocketChannel.open();
//        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(this.host, port);
        this.socketChannel.connect(remote);
    }

    public void request() throws IOException {
        PrintWriter pw = getWriter(socketChannel.socket());
        BufferedReader br = getReader(socketChannel.socket());

        pw.write(HttpUtil.compositeRequest(host));
        pw.flush();
        String msg;
        while ((msg = br.readLine()) != null){
            System.out.println(msg);
        }
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(out);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    private static class HttpConstant {
        public static String[] HOSTS = {"www.baidu.com"};
        public static int PORT = 80;
    }

}
class HttpUtil {

    public static String compositeRequest(String host){

        return "GET / HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: curl/7.43.0\r\n" +
                "Accept: */*\r\n\r\n";
    }

}
