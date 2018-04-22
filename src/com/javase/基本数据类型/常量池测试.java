package com.javase.基本数据类型;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/22.
 */
public class 常量池测试 {

    class A{
        int a = 1;
        Integer i= 127;
    }

    class B{
        int b = 1;
        Integer i= 127;
    }

    @Test
    public void 常量池何时使用() {
        System.out.println(new A().i == new B().i);
    }

    public static void main(String[] args) {

    }
}
