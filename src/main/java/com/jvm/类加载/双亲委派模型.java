package com.jvm.类加载;

/**
 * Created by 周杰伦 on 2018/6/8.
 */
public class 双亲委派模型 {
//    public Class<?> loadClass(String name)throws ClassNotFoundException {
//        return loadClass(name, false);
//    }

//    protected synchronized Class<?> loadClass(String name, boolean resolve)throws ClassNotFoundException {
//        // 首先判断该类型是否已经被加载
//        Class c = findLoadedClass(name);
//        if (c == null) {
//            //如果没有被加载，就委托给父类加载或者委派给启动类加载器加载
//            try {
//                if (parent != null) {
//                    //如果存在父类加载器，就委派给父类加载器加载
//                    c = parent.loadClass(name, false);
//                } else {
//                    //如果不存在父类加载器，就检查是否是由启动类加载器加载的类，通过调用本地方法native Class findBootstrapClass(String name)
//                    c = findBootstrapClass0(name);
//                }
//            } catch (ClassNotFoundException e) {
//                // 如果父类加载器和启动类加载器都不能完成加载任务，才调用自身的加载功能
//                c = findClass(name);
//            }
//        }
//        if (resolve) {
//            resolveClass(c);
//        }
//        return c;
//    }
}
