package com.Java集合;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Created by 周杰伦 on 2018/5/11.
 */
public class HashSet和TreeSet和Linkedhashset {
    static class Key {
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return obj.hashCode() == hashCode();
        }
    }
    public static void main(String[] args) {
        HashSet<Object> hashSet = new HashSet();
        Object o = new Object();
        hashSet.add(new Key());
        hashSet.add(new Key());
        System.out.println(hashSet.size());
        hashSet.add(o);
        hashSet.add(o);
        System.out.println(hashSet.size());
        TreeSet treeSet = new TreeSet();
    }

    @Test
    public void test() {
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(2);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        //元素不能重复，并且遍历时无序
        System.out.println(hashSet.size());
        for (Object i : hashSet) {
            System.out.println(i);
        }

        TreeSet treeSet = new TreeSet();
        User user = new User(1);
        User user2 = new User(2);
        //加入treeset的元素必须支持排序接口。此处会报错
//        java.lang.ClassCastException: com.Java集合.HashSet和TreeSet和Linkedhashset$User cannot be cast to java.lang.Comparable
//
//        at java.util.TreeMap.compare(TreeMap.java:1294)
//        at java.util.TreeMap.put(TreeMap.java:538)
//        at java.util.TreeSet.add(TreeSet.java:255)

//        treeSet.add(user);
//        treeSet.add(user2);
        System.out.println("------");
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(6);
        treeSet.add(8);

        //默认是正序遍历
        for (Object i : treeSet) {
            System.out.println(i);
        }
        System.out.println("-----");
        //逆序遍历
        for (Object i :treeSet.descendingSet()) {
            System.out.println(i);
        }

        System.out.println("------");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new User(1));
        linkedHashSet.add(new User(2));
        linkedHashSet.add(new User(4));
        linkedHashSet.add(new User(3));
        System.out.println(linkedHashSet.size());

        //按照插入顺序遍历
        for (Object i : linkedHashSet) {
            System.out.println(i);
        }

    }



    class User{
        int i;
        User(int i) {
            this.i = i;
        }
        @Override
        public int hashCode() {
            return i;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this;
        }
    }
}
