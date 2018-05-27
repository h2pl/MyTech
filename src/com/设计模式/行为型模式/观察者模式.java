package com.设计模式.行为型模式;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 观察者模式 {
    public static void main(String[] args) {
        // 先定义一个主题
        Subject subject1 = new Subject();
        // 定义观察者
        Observer observer = new BinaryObserver(subject1);
        Observer observer1 = new HexaObserver(subject1);

        // 模拟数据变更，这个时候，观察者们的 update 方法将会被调用
        subject1.setState(11);
    }
}
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // 数据已变更，通知观察者们
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    // 通知观察者们
    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
//当然，jdk 也提供了相似的支持，具体的大家可以参考 java.util.Observable 和 java.util.Observer 这两个类。

//实际生产过程中，观察者模式往往用消息中间件来实现，
//        如果要实现单机观察者模式，笔者建议读者使用 Guava 中的 EventBus，
//        它有同步实现也有异步实现，本文主要介绍设计模式，就不展开说了。
class BinaryObserver extends Observer {

    // 在构造方法中进行订阅主题
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        // 通常在构造方法中将 this 发布出去的操作一定要小心
        this.subject.attach(this);
    }

    // 该方法由主题类在数据变更的时候进行调用
    @Override
    public void update() {
        String result = Integer.toBinaryString(subject.getState());
        System.out.println("订阅的数据发生变化，新的数据处理为二进制值为：" + result);
    }
}

class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        String result = Integer.toHexString(subject.getState()).toUpperCase();
        System.out.println("订阅的数据发生变化，新的数据处理为十六进制值为：" + result);
    }
}
