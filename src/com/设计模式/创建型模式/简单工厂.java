package com.设计模式.创建型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 简单工厂 {

}

interface food {
    void addSpicy(String s);
    void addCondiment(String s);
}

class LanZhouNoodle implements food {
    @Override
    public void addSpicy(String s) {

    }

    @Override
    public void addCondiment(String s) {

    }
}
class HuangMenChicken implements food {

    @Override
    public void addSpicy(String s) {

    }

    @Override
    public void addCondiment(String s) {

    }
}
//简单工厂
class SimpleFactory {

    public static food makeFood(String name) {
        if (name.equals("noodle")) {
            food noodle = new LanZhouNoodle();
            noodle.addSpicy("more");
            return noodle;
        } else if (name.equals("chicken")) {
            food chicken = new HuangMenChicken();
            chicken.addCondiment("potato");
            return chicken;
        } else {
            return null;
        }
    }
}

