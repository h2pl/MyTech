package com.javase.反射;

import javax.management.ReflectionException;
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
        //获取该类所有的方法，包括静态方法，实例方法。
        //此处也包括了私有方法，只不过私有方法在用invoke访问之前要设置访问权限
        //也就是使用setAccessible使方法可访问，否则会抛出异常
//       // IllegalAccessException的解释是
//        * An IllegalAccessException is thrown when an application tries
// * to reflectively create an instance (other than an array),
// * set or get a field, or invoke a method, but the currently
// * executing method does not have access to the definition of
// * the specified class, field, method or constructor.

//        getDeclaredMethod*()获取的是类自身声明的所有方法，包含public、protected和private方法。
//            getMethod*()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。

        //就是说，当这个类，域或者方法被设为私有访问，使用反射调用但是却没有权限时会抛出异常。
        Method[] methods = userBeanClass.getDeclaredMethods(); // 获取所有成员方法
        for (Method method : methods) {
            //反射可以获取方法上的注解，通过注解来进行判断
            if (method.isAnnotationPresent(Invoke.class)) { // 判断是否被 @Invoke 修饰
                //判断方法的修饰符是是static
                if (Modifier.isStatic(method.getModifiers())) { // 如果是 static 方法
                    //反射调用该方法
                    //类方法可以直接调用，不必先实例化
                    method.invoke(null, "wingjay",2); // 直接调用，并传入需要的参数 devName
                } else {
                    //如果不是类方法，需要先获得一个实例再调用方法
                    //传入构造方法需要的变量类型
                    Class[] params = {String.class, long.class};
                    //获取该类指定类型的构造方法
                    //如果没有这种类型的方法会报错
                    Constructor constructor = userBeanClass.getDeclaredConstructor(params); // 获取参数格式为 String,long 的构造函数
                    //通过构造方法的实例来进行实例化
                    Object userBean = constructor.newInstance("wingjay", 11); // 利用构造函数进行实例化，得到 Object
                    if (Modifier.isPrivate(method.getModifiers())) {
                        method.setAccessible(true); // 如果是 private 的方法，需要获取其调用权限
//                        Set the {@code accessible} flag for this object to
//     * the indicated boolean value.  A value of {@code true} indicates that
//     * the reflected object should suppress Java language access
//     * checking when it is used.  A value of {@code false} indicates
//                                * that the reflected object should enforce Java language access checks.
                        //通过该方法可以设置其可见或者不可见，不仅可以用于方法
                        //后面例子会介绍将其用于成员变量
                    }
                    method.invoke(userBean); // 调用 method，无须参数
                }
            }
        }
    }
}
