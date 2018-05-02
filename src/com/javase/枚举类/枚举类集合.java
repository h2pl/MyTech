package com.javase.枚举类;

import java.util.EnumMap;


/**
 * Created by 周杰伦 on 2018/5/2.
 */
public class 枚举类集合 {
    public static void main(String[] args) {
        EnumMap<Color, String> map = new EnumMap<Color, String>(Color.class);
        map.put(Color.Blue, "Blue");
        map.put(Color.Yellow, "Yellow");
        map.put(Color.Red, "Red");
        System.out.println(map.get(Color.Red));
    }
}

