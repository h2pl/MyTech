package com.javase.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by 周杰伦 on 2018/4/1.
 */
public class 使用反射调用方法 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class userBeanClass = UserBean.class;
        Method[] methods = userBeanClass.getDeclaredMethods(); // 获取所有成员方法
        for (Method method : methods) {
            if (method.isAnnotationPresent(Invoke.class)) { // 判断是否被 @Invoke 修饰
                if (Modifier.isStatic(method.getModifiers())) { // 如果是 static 方法
                    method.invoke(null, "wingjay",2); // 直接调用，并传入需要的参数 devName
                } else {
                    Class[] params = {String.class, long.class};
                    Constructor constructor = userBeanClass.getDeclaredConstructor(params); // 获取参数格式为 String,long 的构造函数
                    Object userBean = constructor.newInstance("wingjay", 11); // 利用构造函数进行实例化，得到 Object
                    if (Modifier.isPrivate(method.getModifiers())) {
                        method.setAccessible(true); // 如果是 private 的方法，需要获取其调用权限
                    }
                    method.invoke(userBean); // 调用 method，无须参数
                }
            }
        }
    }
}
