package com.javase.枚举类;

/**
 * Created by 周杰伦 on 2018/5/2.
 */
public enum Color implements Print{
    //每个颜色都是枚举类的一个实例，并且构造方法要和枚举类的格式相符合。
    //如果实例后面有其他内容，实例序列结束时要加分号。
    Red("红色", 1), Green("绿色", 2), Blue("蓝色", 3), Yellow("黄色", 4);
    String name;
    int index;
    Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public void showAllColors() {
        //values是Color实例的数组，在通过index和name可以获取对应的值。
        for (Color color : Color.values()) {
            System.out.println(color.index + ":" + color.name);
        }
    }

    @Override
    public void print() {
        System.out.println(this.name);
    }

    //所有枚举类都继承自Enum类，所以可以重写该类的方法
    @Override
    public String toString() {
        return this.index + ":" + this.name;
    }
}
