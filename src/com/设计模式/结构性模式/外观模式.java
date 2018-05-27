package com.设计模式.结构性模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
//又称门面模式
public class 外观模式 {
    public static void main(String[] args) {
        //不使用门面时，需要自己指定实现类
        // 画一个圆形
        Shape circle = new Circle();
        circle.draw();

        // 画一个长方形
        Shape rectangle = new Rectangle();
        rectangle.draw();
        //使用门面时。只需调用门面的方法

        ShapeMaker shapeMaker = new ShapeMaker();

        // 客户端调用现在更加清晰了
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}
interface Shape {
    void draw();
}
//定义几个实现类：

class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}

class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle::draw()");
    }
}

class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
//
//以上是我们常写的代码，我们需要画圆就要先实例化圆，画长方形就需要先实例化一个长方形，然后再调用相应的 draw() 方法。
//
//        下面，我们看看怎么用门面模式来让客户端调用更加友好一些。
//
//        我们先定义一个门面：
class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    /**
     * 下面定义一堆方法，具体应该调用什么方法，由这个门面来决定
     */

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
