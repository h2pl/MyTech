package com.设计模式.结构性模式.适配器模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 *
 * 适配器模式和代理模式的异同

 比较这两种模式，其实是比较对象适配器模式和代理模式，在代码结构上，
 它们很相似，都需要一个具体的实现类的实例。
 但是它们的目的不一样，代理模式做的是增强原方法的活；
 适配器做的是适配的活，为的是提供“把鸡包装成鸭，然后当做鸭来使用”，
 而鸡和鸭它们之间原本没有继承关系。
 */
public class 对象适配器 {

    public static void main(String[] args) {
        Duck duck = new DuckAdapter(new WildCock());
        duck.fly();
        duck.quack();
    }
}
interface Duck {
    public void quack(); // 鸭的呱呱叫
    public void fly(); // 飞
}

interface Cock {
    public void gobble(); // 鸡的咕咕叫
    public void fly(); // 飞
}

class WildCock implements Cock {
    public void gobble() {
        System.out.println("咕咕叫");
    }
    public void fly() {
        System.out.println("鸡也会飞哦");
    }
}

// 毫无疑问，首先，这个适配器肯定需要 implements Duck，这样才能当做鸭来用
class DuckAdapter implements Duck {

    Cock cock;
    // 构造方法中需要一个鸡的实例，此类就是将这只鸡适配成鸭来用
    public DuckAdapter(Cock cock) {
        this.cock = cock;
    }

    // 实现鸭的呱呱叫方法
    @Override
    public void quack() {
        // 内部其实是一只鸡的咕咕叫
        cock.gobble();
    }

    @Override
    public void fly() {
        cock.fly();
    }
}