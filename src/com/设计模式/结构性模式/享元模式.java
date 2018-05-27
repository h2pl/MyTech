package com.设计模式.结构性模式;

import java.util.HashMap;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 享元模式 {
    public static void main(String[] args) {

    }

}

// 享元模式的核心在于享元工厂类，
// 享元工厂类的作用在于提供一个用于存储享元对象的享元池，
// 用户需要对象时，首先从享元池中获取，
// 如果享元池中不存在，则创建一个新的享元对象返回给用户，
// 在享元池中保存该新增对象。
//
//         复制代码
class FlyweightFactory{
    private HashMap flyweights = new HashMap();

    public Flyweight getFlyweight(String key){
        if(flyweights.containsKey(key)){
            return (Flyweight)flyweights.get(key);
        }
        else{
            Flyweight fw = new ConcreteFlyweight();
            flyweights.put(key,fw);
            return fw;
        }
    }

    private interface Flyweight{
    }

    private class ConcreteFlyweight implements Flyweight {
    }
}
//享元模式
//        英文是 Flyweight Pattern，不知道是谁最先翻译的这个词，感觉这翻译真的不好理解，我们试着强行关联起来吧。Flyweight 是轻量级的意思，享元分开来说就是 共享 元器件，也就是复用已经生成的对象，这种做法当然也就是轻量级的了。
//
//        复用对象最简单的方式是，用一个 HashMap 来存放每次新生成的对象。每次需要一个对象的时候，先到 HashMap 中看看有没有，如果没有，再生成新的对象，然后将这个对象放入 HashMap 中。
//
//        这种简单的代码我就不演示了。
