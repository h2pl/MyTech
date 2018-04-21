package com.javase.反射;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * Created by 周杰伦 on 2018/4/1.
 */
public class 打印所有方法 {

    public static void main(String[] args) {
        Class userBeanClass = UserBean.class;
        Field[] fields = userBeanClass.getDeclaredFields();

        Method[] methods = userBeanClass.getDeclaredMethods();
        for (Method method : methods) {
            String methodString = Modifier.toString(method.getModifiers()) + " " ; // private static
            methodString += method.getReturnType().getSimpleName() + " "; // void
            methodString += method.getName() + "("; // staticMethod
            Class[] parameters = method.getParameterTypes();
            Parameter[] p = method.getParameters();

            for (Class parameter : parameters) {
                methodString += parameter.getSimpleName() + " " ; // String
            }
            methodString += ")";
            System.out.println(methodString);
        }

    }
}
