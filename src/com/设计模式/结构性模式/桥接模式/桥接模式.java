package com.设计模式.结构性模式.桥接模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 桥接模式 {
    //这回大家应该就知道抽象在哪里，怎么解耦了吧。桥梁模式的优点也是显而易见的，就是非常容易进行扩展。
    public static void main(String[] args) {
        //DrawAPI这个接口相当于图形和颜色的桥接器，图形和颜色解耦，并且可以自由组合。
        //当然，DrawAPI还可以用于图形的各种绘制，比如设置长宽高，如何绘制等等，只要实现其接口就可以随意扩展了。
        DrawAPI api = new Red();
        DrawAPI api1 = new Blue();
        Shape shape = new Circle(api);
        Shape shape1 = new Rectangle(api1);
        shape.draw();
        shape1.draw();
    }
}

interface DrawAPI {
    void draw();
}
class Red implements DrawAPI {
    @Override
    public void draw() {
        System.out.println("red");
    }
}
class Blue implements DrawAPI {
    @Override
    public void draw() {
        System.out.println("blue");

    }
}
abstract class Shape{
    protected DrawAPI api;
    Shape(DrawAPI api){
        this.api = api;
    }
    abstract void draw();
}

class Circle extends Shape {

    Circle(DrawAPI api) {
        super(api);
    }

    @Override
    void draw() {
        api.draw();
        System.out.println("Circle");
    }
}
class Rectangle extends Shape {

    Rectangle(DrawAPI api) {
        super(api);
    }

    @Override
    void draw() {
        api.draw();
        System.out.println("Rectangle");
    }
}
