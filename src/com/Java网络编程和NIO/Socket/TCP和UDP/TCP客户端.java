package com.Java网络编程和NIO.Socket.TCP和UDP;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class TCP客户端 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket("127.0.0.1",1234);

                    //构建IO
                    InputStream is = s.getInputStream();
                    OutputStream os = s.getOutputStream();

                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    //向服务器端发送一条消息
                    bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
                    bw.flush();

                    //读取服务器返回的消息
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String mess = br.readLine();
                    System.out.println("服务器："+mess);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
