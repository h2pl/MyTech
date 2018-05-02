package com.javase.枚举类;

/**
 * Created by 周杰伦 on 2018/5/2.
 */
public class 用接口组织枚举 {
    public static void main(String[] args) {
        Food cf = chineseFood.dumpling;
        Food jf = Food.JapaneseFood.fishpiece;
        for (Food food : chineseFood.values()) {
            System.out.println(food);
        }
        for (Food food : Food.JapaneseFood.values()) {
            System.out.println(food);
        }
    }
}
interface Food {
    enum JapaneseFood implements Food {
        suse, fishpiece
    }
}
enum chineseFood implements Food {
    dumpling, tofu
}
