package com.Java网络编程和NIO.NIO.Channel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class DatagramChannelTest {
    public static void main(String[] args) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            byte []arr = "hello world".getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            byteBuffer.put(new byte[] {1,2,3,4});
            byteBuffer.flip();
            DatagramPacket datagramPacket = new DatagramPacket(arr, 0, arr.length);
            datagramChannel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 1234));
            datagramChannel.disconnect();


            datagramChannel.socket().bind(new InetSocketAddress("127.0.0.1", 1234));
            datagramChannel.read(byteBuffer);
            System.out.println(Arrays.toString(byteBuffer.array()));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
