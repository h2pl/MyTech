package com.javase.面向对象.封装.ali;

import com.javase.面向对象.封装.baidu.Moneyb;

/**
 * Created by 周杰伦 on 2018/4/21.
 */
public class Moneya {
    protected int alimoney;
    private int ipo;
    public static void main(String[] args) {
        Moneyb moneyb = new Moneyb();
        System.out.println(moneyb.chinamoney);
        //baidumoney是protected修饰，
        //money不加修饰符就是默认protected修饰，也是包内共享
    }
}
