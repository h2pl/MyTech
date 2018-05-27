package com.Java网络编程和NIO.异步IO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.util.concurrent.Future;

/**
 * Created by 周杰伦 on 2018/5/27.
 */
public class AsynchronousChannelTest {
    public static void main(String[] args) {
        try {
            AsynchronousFileChannel channel = AsynchronousFileChannel.open(Paths.get("/Users/hongjie/test.txt"));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Future<Integer> result = channel.read(buffer, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
