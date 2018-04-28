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
        //注意，打印方法时无法得到局部变量的名称，因为jvm只知道它的类型
        Method[] methods = userBeanClass.getDeclaredMethods();
        for (Method method : methods) {
            //依次获得方法的修饰符，返回类型和名称，外加方法中的参数
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
        //注意方法只能获取到其类型，拿不到变量名
/*        public String getName()
        public long getId()
        public static void staticMethod(String int )
        public void publicMethod()
        private void privateMethod()*/
    }
}
