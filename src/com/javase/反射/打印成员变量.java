package com.javase.反射;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by 周杰伦 on 2018/4/1.
 */
public class 打印成员变量 {
    public static void main(String[] args) {
        Class userBeanClass = UserBean.class;
        //获得该类的所有成员变量，包括static private
        Field[] fields = userBeanClass.getDeclaredFields();

        for(Field field : fields) {
            //private属性即使不用下面这个语句也可以访问
//            field.setAccessible(true);

            //因为类的私有域在反射中默认可访问，所以flag默认为true。
            String fieldString = "";
            fieldString += Modifier.toString(field.getModifiers()) + " "; // `private`
            fieldString += field.getType().getSimpleName() + " "; // `String`
            fieldString += field.getName(); // `userName`
            fieldString += ";";
            System.out.println(fieldString);
//            public String userName;
//            protected int i;
//            static int j;
//            private int l;
//            private long userId;
            //public String userName;
//            protected int i;
//            static int j;
//            private int l;
//            private long userId;

        }

    }
}
