package com.javase.反射;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by 周杰伦 on 2018/4/1.
 */
public class 打印成员变量 {
    public static void main(String[] args) {
        Class userBeanClass = UserBean.class;
        Field[] fields = userBeanClass.getDeclaredFields();
        for(Field field : fields) {
            String fieldString = "";
            fieldString += Modifier.toString(field.getModifiers()) + " "; // `private`
            fieldString += field.getType().getSimpleName() + " "; // `String`
            fieldString += field.getName(); // `userName`
            fieldString += ";";
            System.out.println(fieldString);
        }

    }
}
