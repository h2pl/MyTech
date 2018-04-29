package com.javase.泛型;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/28.
 */
public class 泛型通配符与边界 {
    public void showKeyValue(Generic<Number> obj){
        System.out.println("key value is " + obj.getKey());
    }
    @Test
    public void main() {
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);
        showKeyValue(gNumber);
        //泛型中的子类也无法作为父类引用传入
//        showKeyValue(gInteger);
    }
    //直接使用？通配符可以接受任何类型作为泛型传入
    public void showKeyValueYeah(Generic<?> obj) {
        System.out.println(obj);
    }
    //只能传入number的子类或者number
    public void showKeyValue1(Generic<? extends Number> obj){
        System.out.println(obj);
    }

    //只能传入Integer的父类或者Integer
    public void showKeyValue2(Generic<? super Integer> obj){
        System.out.println(obj);
    }

    @Test
    public void testup () {
        //这一行代码编译器会提示错误，因为String类型并不是Number类型的子类
        //showKeyValue1(generic1);
        Generic<String> generic1 = new Generic<String>("11111");
        Generic<Integer> generic2 = new Generic<Integer>(2222);
        Generic<Float> generic3 = new Generic<Float>(2.4f);
        Generic<Double> generic4 = new Generic<Double>(2.56);

        showKeyValue1(generic2);
        showKeyValue1(generic3);
        showKeyValue1(generic4);
    }

    @Test
    public void testdown () {

        Generic<String> generic1 = new Generic<String>("11111");
        Generic<Integer> generic2 = new Generic<Integer>(2222);
        Generic<Number> generic3 = new Generic<Number>(2);
//        showKeyValue2(generic1);本行报错，因为String并不是Integer的父类
        showKeyValue2(generic2);
        showKeyValue2(generic3);
    }
}
