package com.javase.字符串;

/**
 * Created by 周杰伦 on 2018/4/22.
 */
public class 两个字符串包装类的比较 {
    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("1");
        StringBuffer sb2 = new StringBuffer("2");
        String a = "a";
        sb2.append(a);

        System.out.println(sb2);
        System.out.println(sb1.append(a));
    }
}
