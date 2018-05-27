package com.设计模式.创建型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 工厂模式 {
}
interface FoodFactory {
    Food makeFood(String name);
}
interface Food {

}
class ChineseFoodA implements Food {

}
class ChineseFoodB implements Food {

}
class AmericanFoodA implements Food {

}
class AmericanFoodB implements Food {

}
class ChineseFoodFactory implements FoodFactory {

    @Override
    public Food makeFood(String name) {
        if (name.equals("A")) {
            return new ChineseFoodA();
        } else if (name.equals("B")) {
            return new ChineseFoodB();
        } else {
            return null;
        }
    }
}
class AmericanFoodFactory implements FoodFactory {

    @Override
    public Food makeFood(String name) {
        if (name.equals("A")) {
            return new AmericanFoodA();
        } else if (name.equals("B")) {
            return new AmericanFoodB();
        } else {
            return null;
        }
    }
}