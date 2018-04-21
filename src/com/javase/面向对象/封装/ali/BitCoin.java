package com.javase.面向对象.封装.ali;

import com.javase.面向对象.封装.baidu.Moneyb;

/**
 * Created by 周杰伦 on 2018/4/21.
 */
public class BitCoin extends Moneya{
    public static void main(String[] args) {
        Moneya a = new Moneya();
        System.out.println(a.alimoney);
        //父类私有的变量无法读取
    }
}
