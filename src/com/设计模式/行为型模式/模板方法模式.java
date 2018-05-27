package com.设计模式.行为型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 模板方法模式 {
    public static void main(String[] args) {
        AbstractTemplate t = new ConcreteTemplate();
        // 调用模板方法
        t.templateMethod();
    }
}
abstract class AbstractTemplate {
    // 这就是模板方法
    public void templateMethod(){
        init();
        apply(); // 这个是重点
        end(); // 可以作为钩子方法
    }
    protected void init() {
        System.out.println("init 抽象层已经实现，子类也可以选择覆写");
    }
    // 留给子类实现
    protected abstract void apply();
    protected void end() {
    }
}
class ConcreteTemplate extends AbstractTemplate {
    public void apply() {
        System.out.println("子类实现抽象方法 apply");
    }
    public void end() {
        System.out.println("我们可以把 method3 当做钩子方法来使用，需要的时候覆写就可以了");
    }
}
