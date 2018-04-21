package com.jvm.classload;

/**
 * Created by 周杰伦 on 2018/4/1.
 */
public class MusicPlayer {
    public static void main(String[] args) {
        try {
            loadClass();
            printParent();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void print() {
        System.out.printf("Hi I'm MusicPlayer");
    }
    private static void loadClass() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.jvm.classload.MusicPlayer");
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.printf("ClassLoader is %s", classLoader.getClass().getSimpleName());
    }

    private static void printParent() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.jvm.classload.MusicPlayer");
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.printf("currentClassLoader is %s\n", classLoader.getClass().getSimpleName());
        while (classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
            System.out.printf("Parent is %s\n", classLoader.getClass().getSimpleName());
        }
    }
}