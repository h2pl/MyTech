package com.Java并发.synchronized和volatile和final;

import sun.misc.Unsafe;

/**
 * Created by 周杰伦 on 2018/5/20.
 */
public class Cas操作 {
    public static void main(String[] args) {

        //还有这种操作？？惊呆了啊！可以用于算法
        retry:
        for (int j = 0;j < 10;j ++) {
            break retry;
        }
        int i = 0;
        Unsafe.getUnsafe().compareAndSwapInt(i,2,1,1);
    }
}
