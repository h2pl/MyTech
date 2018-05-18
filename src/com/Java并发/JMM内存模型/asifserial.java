package com.Java并发.JMM内存模型;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/5/18.
 */
public class asifserial {
    public void go() {
        int a = 1;
        int b = 2;
        a = b;
        b = a + 1;
        a = b + 2;
        b = a + 2;
        System.out.println(b);
    }
    @Test
    public void test() {
        //单线程遵循as if serial允许重排序但是结果相同
        asifserial a = new asifserial();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.go();
            }
        }).start();
    }

    //多线程不保证顺序执行，只遵循happensbefore
    @Test
    public void test2() {
        asifserial a = new asifserial();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.go();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.go();
            }
        }).start();
    }

//    1.程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作
//      略
//　　2.监视器锁规则：对一个锁的解锁，happens-before于随后对这个锁的加锁
//      意思就是同步代码块要先加锁再解锁
//　　3.volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读
//         例子请看下面


//　　4.传递性：如果A happens-before B，且B happens-before C，那么A happens-before C
//      略

//　　5.start规则：如果线程A执行操作ThreadB.start()（启动线程B），那么A线程的ThreadB.start()操作happens-before于线程B中的任意操作
//      意思是就是线程要先start，才能执行其中的run方法

//　　6.join规则：如果线程A执行操作ThreadB.join()并成功返回，那么线程B中的任意操作happens-before于线程A从ThreadB.join()操作成功返回。
    //意思就是，先让线程B执行完run方法，才会返回结果，保证返回时线程已经执行完毕


}

//volatile读在写之后
class vo {
    volatile int i = 0;
    public void set(int n) {
        i = n;

    }
    public int get() {
        return i;
    }

    @Test
    public void test() {
        vo v = new vo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(v.get());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                v.set(2);
            }
        }).start();
    }
}

class join{
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a");
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("b");
            }
        });
        //即使b在a之前start，也会先执行join的a的代码
        b.start();
        a.start();


    }
}
