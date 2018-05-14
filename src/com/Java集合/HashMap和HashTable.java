package com.Java集合;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Created by 周杰伦 on 2018/5/8.
 */
public class HashMap和HashTable {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        Hashtable hashtable = new Hashtable();
    }

    @Test
    public void test() {
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
        HashMap hashMap = new HashMap();
        System.out.println(hashMap.size());
        for (int i = 0;i < 10;i ++) {
            User user = new User(1);
            //key不相等但是equals相同，那么就会连成链表
            hashMap.put(user, new Integer(2));
        }
        System.out.println(hashMap.size());
        //0
        //插入十个key，hashcode相等但是equals不相等，所以连成链表。size只返回实际元素个数
        //10
        hashMap.put(new User(2), 2);
        hashMap.put(new User(3), 3);
        hashMap.put(new User(4), 4);
        System.out.println(hashMap.size());
        //扩容无法在代码中体现

        //key和value
        Set set = hashMap.keySet();
        for (Object user : set) {
            System.out.println(user);
        }
        Set entry = hashMap.entrySet();

        Collection list = hashMap.values();
        for (Object i : list) {
            System.out.println(i);
        }

        //打印结果
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@1
//        com.Java集合.HashMap和HashTable$1User@2
//        com.Java集合.HashMap和HashTable$1User@3
//        com.Java集合.HashMap和HashTable$1User@4
//        2
//        2
//        2
//        2
//        2
//        2
//        2
//        2
//        2
//        2
//        2
//        3
//        4
    }

    @Test
    public void test1() {
        Hashtable hashtable = new Hashtable();
        hashtable.put(1,2);
        //与hashmap类似，只是方法改为线程安全了
    }

}

