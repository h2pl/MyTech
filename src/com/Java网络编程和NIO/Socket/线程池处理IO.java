package com.Java网络编程和NIO.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 线程池处理IO {
}
class ThreadPoolApplication {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(8);

        for (final String host: HttpConstant.HOSTS) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    new SocketHttpClient().start(host, HttpConstant.PORT);
                }
            });

            executorService.submit(t);
            new SocketHttpClient().start(host, HttpConstant.PORT);

        }

    }

    private static class HttpConstant {
        public static String[] HOSTS;
        public static int PORT;
    }
}