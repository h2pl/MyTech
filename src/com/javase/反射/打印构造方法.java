package com.javase.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * Created by 周杰伦 on 2018/4/1.
 */
public class 打印构造方法 {
    public static void main(String[] args) {
        // constructors
        Class<?> clazz = UserBean.class;

        Class userBeanClass = UserBean.class;
        //获得所有的构造方法
        Constructor[] constructors = userBeanClass.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String s = Modifier.toString(constructor.getModifiers()) + " ";
            s += constructor.getName() + "(";
            //构造方法的参数类型
            Class[] parameters = constructor.getParameterTypes();
            for (Class parameter : parameters) {
                s += parameter.getSimpleName() + ", ";
            }
            s += ")";
            System.out.println(s);
        }
    }
}
