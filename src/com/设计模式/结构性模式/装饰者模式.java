package com.设计模式.结构性模式;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 装饰者模式 {
    //IO流中的装饰者模式
    @Test
    public void test() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("a");
        inputStream = new BufferedInputStream(inputStream);
    }
    public static void main(String[] args) {
        // 首先，我们需要一个基础饮料，红茶、绿茶或咖啡
        Beverage beverage = new GreenTea();
        // 开始装饰
        beverage = new Lemon(beverage); // 先加一份柠檬
        beverage = new Mango(beverage); // 再加一份芒果

        System.out.println(beverage.getDescription() + " 价格：￥" + beverage.cost());
        //"绿茶, 加柠檬, 加芒果 价格：￥16"
    }
}
abstract class Beverage {
    // 返回描述
    public abstract String getDescription();
    // 返回价格
    public abstract double cost();
}
class BlackTea extends Beverage {
    public String getDescription() {
        return "红茶";
    }
    public double cost() {
        return 10;
    }
}
class GreenTea extends Beverage {
    public String getDescription() {
        return "绿茶";
    }
    public double cost() {
        return 11;
    }
}
// 咖啡省略

// 调料
abstract class Condiment extends Beverage {

}

class Lemon extends Condiment {
    private Beverage beverage;
    // 这里很关键，需要传入具体的饮料，如需要传入没有被装饰的红茶或绿茶，
    // 当然也可以传入已经装饰好的芒果绿茶，这样可以做芒果柠檬绿茶
    public Lemon(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription() {
        // 装饰
        return beverage.getDescription() + ", 加柠檬";
    }
    public double cost() {
        // 装饰
        return beverage.cost() + 2; // 加柠檬需要 2 元
    }
}
class Mango extends Condiment {
    private Beverage beverage;
    public Mango(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription() {
        return beverage.getDescription() + ", 加芒果";
    }
    public double cost() {
        return beverage.cost() + 3; // 加芒果需要 3 元
    }
}
// 给每一种调料都加一个类