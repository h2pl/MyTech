package com.Java网络编程和NIO.NIO.Buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        IntBuffer intBuf = IntBuffer.allocate(1024);
        LongBuffer longBuf = LongBuffer.allocate(1024);
        intBuf.put(1);
        intBuf.put(2);

        //写
        System.out.println(intBuf.position());
        System.out.println(intBuf.limit());
        System.out.println(intBuf.capacity());

        //读
        intBuf.flip();
        System.out.println(intBuf.position());
        System.out.println(intBuf.limit());
        System.out.println(intBuf.capacity());

        intBuf.clear();
        intBuf.put(new int[] {1,2,3,4});
        System.out.println(intBuf.get(1));

        intBuf.flip();
        System.out.println(intBuf.get(1));

        intBuf.compact();
        System.out.println(intBuf.position());

        intBuf.mark();
        intBuf.put(new int[] {5,6,7,8});
        System.out.println(intBuf.position());
        intBuf.reset();
        System.out.println(intBuf.position());
    }
}
