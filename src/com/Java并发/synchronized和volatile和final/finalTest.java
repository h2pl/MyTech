package com.Java并发.synchronized和volatile和final;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/5/18.
 */
public class finalTest {
    class User{
        int i = 0;
        int b = 1;
    }

    //final初始化会在构造方法返回前执行。
    final User user = null;
    final int i = 0;
    final int[] a = new int[2];
    public finalTest(int i, int b) {
        i = 2;
        user.b = b;
    }

    @Test
    public void test() {
        finalTest f = new finalTest(1,2);
        System.out.println(f);
    }
}
