package com.javase.基本数据类型;

/**
 * Created by 周杰伦 on 2018/4/21.
 */
public class 基本数据类型的常量池 {
    //基本数据类型的常量池是-128到127之间。
    // 在这个范围中的基本数据类的包装类可以自动拆箱，比较时直接比较数值大小。
    public static void main(String[] args) {
        //int的自动拆箱和装箱只在-128到127范围中进行，超过该范围的两个integer的 == 判断是会返回false的。
        Integer a1 = 128;
        Integer a2 = -128;
        Integer a3 = -128;
        Integer a4 = 128;
        System.out.println(a1 == a4);
        System.out.println(a2 == a3);

        Byte b1 = 127;
        Byte b2 = 127;
        Byte b3 = -128;
        Byte b4 = -128;
        //byte都是相等的，因为范围就在-128到127之间
        System.out.println(b1 == b2);
        System.out.println(b3 == b4);

        //
        Long c1 = 128L;
        Long c2 = 128L;
        Long c3 = -128L;
        Long c4 = -128L;
        System.out.println(c1 == c2);
        System.out.println(c3 == c4);

        //char没有负值
        //发现char也是在0到127之间自动拆箱
        Character d1 = 128;
        Character d2 = 128;
        Character d3 = 127;
        Character d4 = 127;
        System.out.println(d1 == d2);
        System.out.println(d3 == d4);


        Integer i = 10;
        Byte b = 10;
        //比较Byte和Integer.两个对象无法直接比较，报错
//        System.out.println(i == b);
        System.out.println("i == b " + i.equals(b));
        //答案是false,因为包装类的比较时先比较是否是同一个类，不是的话直接返回false.
        int ii = 128;
        short ss = 128;
        long ll = 128;
        char cc = 128;
        System.out.println("ii == bb " + (ii == ss));
        System.out.println("ii == ll " + (ii == ll));
        System.out.println("ii == cc " + (ii == cc));
        //这时候都是true，因为基本数据类型直接比较值，值一样就可以。
    }
}
