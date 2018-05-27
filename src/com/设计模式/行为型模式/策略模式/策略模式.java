package com.设计模式.行为型模式.策略模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 策略模式 {
    public static void main(String[] args) {
        Strategy strategy = new RedPen();
        Context context = new Context(strategy);
        context.executeDraw(1,2,3);
    }
}

interface Strategy {
    public void draw(int radius, int x, int y);
}

class RedPen implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用红色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}
class GreenPen implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用绿色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}
class BluePen implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用蓝色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public void executeDraw(int radius, int x, int y){
        strategy.draw(radius, x, y);
    }
}