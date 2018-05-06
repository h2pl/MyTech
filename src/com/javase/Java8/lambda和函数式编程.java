package com.javase.Java8;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class lambda和函数式编程 {
    @Test
    public void test1() {
        List names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(Arrays.toString(names.toArray()));
    }

    @Test
    public void test2() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(Arrays.toString(names.toArray()));
    }

    public static void execute(Runnable runnable) {
        runnable.run();
    }

    public static void main(String[] args) {
        //Java8之前
        execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });

        //使用Lambda表达式
        execute(() -> System.out.println("run"));
    }

}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class A {
    public static void main(String[] args) {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);    // 123
    }

}
