package com.Java网络编程和NIO.Socket;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 多线程处理IO {
}
class MultiThreadApplication {

    public static void main(String[] args) {

        for (final String host: HttpConstant.HOSTS) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    new SocketHttpClient().start(host, HttpConstant.PORT);
                }
            });

            t.start();

        }
    }

    private static class HttpConstant {
        public static String[] HOSTS;
        public static int PORT;
    }
}