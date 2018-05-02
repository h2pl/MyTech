package com.javase.枚举类;

/**
 * Created by 周杰伦 on 2018/5/2.
 */
public class 元素个数 {
}
enum Clothes {
//    枚举类实例之前不能有别的代码
    //枚举类可以持有多个变量值
    //枚举实例不能用static修饰，也不能用其他修饰符修饰。

    skirt("Prada",1111,'M'), shoes("LV",11111,'L'), cloth("Armani",2111,'S'), blouse("Gucci",1111,'M');
    String brand;
    int price;
    char size;
    //枚举类的静态变量不能被实例访问
    static int i = 0;

    private Clothes(String brand, int price, char size) {
        this.brand = brand;
        this.price = price;
        this.size = size;
    }
    public void clearBucket() {
        int sum = 0;
        for (Clothes clothes : Clothes.values()) {
            sum += clothes.price;
        }
        System.out.println(sum);
    }
}