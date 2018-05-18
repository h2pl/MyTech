package com.Java并发.并发三问题;

/**
 * Created by 周杰伦 on 2018/5/17.
 */
public class 原子性 {
}
//volatile保证64位变量的原子性，注意，只保证单个变量操作时的原子性
//volatile++这种操作不是原子操作.
class VolatileFeaturesExample {
    //使用volatile声明64位的long型变量
    volatile long vl = 0L;

    public void set(long l) {
        vl = l;   //单个volatile变量的写
    }

    public void getAndIncrement () {
        vl++;    //复合（多个）volatile变量的读/写
    }

    public long get() {
        return vl;   //单个volatile变量的读
    }
}

class VolatileFeaturesExample2 {
    long vl = 0L;               // 64位的long型普通变量

    //对单个的普通 变量的写用同一个锁同步
    public synchronized void set(long l) {
        vl = l;
    }

    public void getAndIncrement () { //普通方法调用
        long temp = get();           //调用已同步的读方法
        temp += 1L;                  //普通写操作
        set(temp);                   //调用已同步的写方法
    }
    public synchronized long get() {
        //对单个的普通变量的读用同一个锁同步
        return vl;
    }
}
