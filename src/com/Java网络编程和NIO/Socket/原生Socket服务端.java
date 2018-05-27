package com.Java网络编程和NIO.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class 原生Socket服务端 {

}
class SocketHttpServer {
    public void start(String host, int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 初始化 ServerSocket
        while (true) {

            try {
                Socket socket = serverSocket.accept();
                // 发送数据
                PrintWriter writer = getWriter(socket);
                System.out.println("hello Client");
                writer.write("hello Client");
                writer.flush();

                // 读取响应
                String msg;
                BufferedReader reader = getReader(socket);
                while ((msg = reader.readLine()) != null) {
                    System.out.println(msg);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(out));
    }
}