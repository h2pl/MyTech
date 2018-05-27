package com.设计模式.结构性模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 代理模式 {
//    我们发现没有，代理模式说白了就是做 “方法包装” 或做 “方法增强”。
// 在面向切面编程中，算了还是不要吹捧这个名词了，在 AOP 中，
// 其实就是动态代理的过程。比如 Spring 中，
// 我们自己不定义代理类，但是 Spring 会帮我们动态来定义代理，
// 然后把我们定义在 @Before、@After、@Around 中的代码逻辑动态添加到代理中。
    public static void main(String[] args) {
        FoodService foodService = new FoodServiceProxy();
        foodService.makeChicken();
        foodService.makeNoodle();
    }
}

interface Food {

    void setChicken(String s);

    void setSpicy(String s);

    void setSalt(String s);

    void setNoodle(String s);

    void addCondiment(String pepper);
}

class Chicken implements Food {

    @Override
    public void setChicken(String s) {

    }

    @Override
    public void setSpicy(String s) {

    }

    @Override
    public void setSalt(String s) {

    }

    @Override
    public void setNoodle(String s) {

    }

    @Override
    public void addCondiment(String pepper) {

    }
}

class Noodle implements Food {

    @Override
    public void setChicken(String s) {

    }

    @Override
    public void setSpicy(String s) {

    }

    @Override
    public void setSalt(String s) {

    }

    @Override
    public void setNoodle(String s) {

    }

    @Override
    public void addCondiment(String pepper) {

    }
}
interface FoodService {
    Food makeChicken();
    Food makeNoodle();
}

class FoodServiceImpl implements FoodService {
    public Food makeChicken() {
        Food f = new Chicken();
        f.setChicken("1kg");
        f.setSpicy("1g");
        f.setSalt("3g");
        return f;
    }
    public Food makeNoodle() {
        Food f = new Noodle();
        f.setNoodle("500g");
        f.setSalt("5g");
        return f;
    }
}

// 代理要表现得“就像是”真实实现类，所以需要实现 FoodService
class FoodServiceProxy implements FoodService {

    // 内部一定要有一个真实的实现类，当然也可以通过构造方法注入
    private FoodService foodService = new FoodServiceImpl();

    public Food makeChicken() {
        System.out.println("我们马上要开始制作鸡肉了");

        // 如果我们定义这句为核心代码的话，那么，核心代码是真实实现类做的，
        // 代理只是在核心代码前后做些“无足轻重”的事情
        Food food = foodService.makeChicken();

        System.out.println("鸡肉制作完成啦，加点胡椒粉"); // 增强
        food.addCondiment("pepper");

        return food;
    }
    public Food makeNoodle() {
        System.out.println("准备制作拉面~");
        Food food = foodService.makeNoodle();
        System.out.println("制作完成啦");
        return food;
    }
}