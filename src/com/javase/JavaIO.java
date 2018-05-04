package com.javase;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 周杰伦 on 2018/5/4.
 */
public class JavaIO {

    @Test
    //文件流范例，打开一个文件的输入流，读取到字节数组，再写入另一个文件的输出流
    public void test1() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("a.txt"));
            FileOutputStream fileOutputStream = new FileOutputStream(new File("b.txt"));
            byte []buffer = new byte[128];
            while (fileInputStream.read(buffer) != -1) {
                fileOutputStream.write(buffer);
            }
            //随机读写，通过mode参数来决定读或者写
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File("c.txt"), "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //使用管道来完成两个线程间的数据点对点传递
    @Test
    public void test2() throws IOException {
        PipedInputStream pipedInputStream = new PipedInputStream();
        PipedOutputStream pipedOutputStream = new PipedOutputStream(pipedInputStream);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pipedOutputStream.write("hello input".getBytes());
                    pipedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte []arr = new byte[128];
                    while (pipedInputStream.read(arr) != -1) {
                        System.out.println(Arrays.toString(arr));
                    }
                    pipedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //从网络中读取字节流也可以直接使用OutputStream
    public void test3() {
        //读取网络进程的输出流
        OutputStream outputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }
        };
    }
    public void process(OutputStream ouput) throws IOException {
        //处理网络信息
        //do something with the OutputStream
    }

    //字符数组和字节数组在io过程中的作用
    public void test4() {
        //arr和brr分别作为数据源
        char []arr = {'a','c','d'};
        CharArrayReader charArrayReader = new CharArrayReader(arr);
        byte []brr = {1,2,3,4,5};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(brr);
    }

    //测试System.in, System.out, System.err
    public static void main(String[] args) {
        int in = new Scanner(System.in).nextInt();
        System.out.println(in);
        System.out.println("out");
        System.err.println("err");
        //输入10，结果是
//        err（红色）
//        10
//        out
    }

    public void test5() throws FileNotFoundException {
        Reader input1 = new BufferedReader(new FileReader("c:\\data\\input-file.txt"));
        Reader input2 = new BufferedReader(new FileReader("c:\\data\\input-file.txt"), 8 * 1024);
    }
}
