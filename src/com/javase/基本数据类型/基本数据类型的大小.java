package com.javase.基本数据类型;

/**
 * Created by 周杰伦 on 2018/4/22.
 */
public class 基本数据类型的大小 {
    public static void main(String[] args) {
        //8位
        byte bx = Byte.MAX_VALUE;
        byte bn = Byte.MIN_VALUE;
        //16位
        short sx = Short.MAX_VALUE;
        short sn = Short.MIN_VALUE;
        //32位
        int ix = Integer.MAX_VALUE;
        int in = Integer.MIN_VALUE;
        //64位
        long lx = Long.MAX_VALUE;
        long ln = Long.MIN_VALUE;
        //32位
        float fx = Float.MAX_VALUE;
        float fn = Float.MIN_VALUE;
        //64位
        double dx = Double.MAX_VALUE;
        double dn = Double.MIN_VALUE;
        //16位
        char cx = Character.MAX_VALUE;
        char cn = Character.MIN_VALUE;
        //1位
        boolean bt = Boolean.TRUE;
        boolean bf = Boolean.FALSE;
        System.out.println(cx);
        System.out.println(cn);
        System.out.println(bt);
        System.out.println(bf);
        System.out.println(bx);
        System.out.println(bn);
        System.out.println(ix);
        System.out.println(in);
        System.out.println(sx);
        System.out.println(sn);
        System.out.println(lx);
        System.out.println(ln);

        System.out.println(fx);
        System.out.println(fn);
        System.out.println(dx);
        System.out.println(dn);
    }
}
