package com.jvm.内存泄漏与内存溢出;

import org.junit.jupiter.api.Test;

import java.util.Vector;

/**
 * Created by 周杰伦 on 2018/6/13.
 */

//常见内存泄漏
public class 内存泄漏 {
    public static void main(String[] args) {
        int i = 0 ;
        while (true) {
            System.out.println(++i);
        }
    }

    @Test
    public void test1() {
        Vector vector = new Vector();
        while (true) {
            Object o = new Object();
            vector.add(o);
            o = null;
        }
    }
}
