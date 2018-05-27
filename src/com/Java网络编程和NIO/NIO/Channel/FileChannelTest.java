package com.Java网络编程和NIO.NIO.Channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class FileChannelTest {
    public static void main(String[] args) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("/data.txt"));
            FileChannel fileChannel = inputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            fileChannel.write(byteBuffer);
            fileChannel.read(byteBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
