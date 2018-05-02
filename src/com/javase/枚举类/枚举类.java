package com.javase.枚举类;


/**
 * Created by 周杰伦 on 2018/5/2.
 */
public class 枚举类 {
    public static void main(String[] args) {
        showColor(Color.Blue);
        Color color = Color.Blue;
        color.showAllColors();
        System.out.println(color);

    }
    public static void showColor(Color color) {
        switch (color) {
            case Red:
                System.out.println(color);
                break;
            case Blue:
                System.out.println(color);
                break;
            case Yellow:
                System.out.println(color);
                break;
            case Green:
                System.out.println(color);
                break;
        }
    }
}
//枚举类不能再继承别的类，但是可以实现接口。

