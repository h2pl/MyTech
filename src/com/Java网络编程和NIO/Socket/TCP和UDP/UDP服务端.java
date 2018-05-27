package com.Java网络编程和NIO.Socket.TCP和UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class UDP服务端 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket datagramSocket = new DatagramSocket(1234);
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(packet);
                    System.out.println("server recv");
                    String msg = new String(packet.getData(), "utf-8");
                    System.out.println(msg);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
