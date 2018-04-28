package com.javase.异常;

import java.util.concurrent.ExecutionException;

/**
 * Created by 周杰伦 on 2018/4/27.
 */
public class finally使用 {
    public static void main(String[] args) {
        try {
            throw new IllegalAccessException();
        }catch (IllegalAccessException e) {
            // throw new Throwable();
            //此时如果再抛异常，finally无法执行，只能报错。
            //finally无论何时都会执行
            //除非我显示调用。此时finally才不会执行
            System.exit(0);

        }finally {
            System.out.println("算你狠");
        }
    }
}
