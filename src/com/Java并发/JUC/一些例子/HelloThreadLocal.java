package com.Java并发.JUC.一些例子;

import java.io.IOException;

public class HelloThreadLocal {

    public static void main(String args[]) throws IOException {
//        testThreadLocalVariable();

        testOutsideVariable();
    }

    static int innitialValue = 0;
    static ThreadLocal<Integer> threadLocalValue = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 10;
        }
    };
    private static void testOutsideVariable() {
        for (int i=0; i<100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    innitialValue += 10;
                    threadLocalValue.set(threadLocalValue.get() + 10);
                    System.out.println("innitialValue: " + innitialValue +
                            ", threadLocalValue: " + threadLocalValue.get() +
                            ", in " + Thread.currentThread().getName());
                }
            }).start();
        }
    }

    private static void testThreadLocalVariable() {
        CommonRunnable runnable = new CommonRunnable();
        for (int i=0; i<100; i++) {
            new Thread(runnable).start();
        }
    }

    static class CommonRunnable implements Runnable {
        int localInt = 0;
        ThreadLocal<Integer> threadLocalInt = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };

        @Override
        public void run() {
            localInt += 10;
            threadLocalInt.set(threadLocalInt.get() + 10);
            System.out.println("localInt: " + localInt +
                    ", threadLocalCount: " + threadLocalInt.get()+
                    ", in " + Thread.currentThread().getName());
        }
    }

}
