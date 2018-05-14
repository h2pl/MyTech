package com.Java集合;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Created by 周杰伦 on 2018/5/8.
 */
public class TreeMap和LinkedHashMap {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
//        Arrays.sort();
    }

    @Test
    public void testTree() {
        TreeMap treeMap = new TreeMap();
        treeMap.put(1,3);
        treeMap.put(3,5);
        treeMap.put(100,1);
        treeMap.put(20,66);
        //逆序
        for (Object i : treeMap.descendingKeySet()) {
            System.out.println(i);
        }
        System.out.println("-----");
        //顺序
        for (Object i : treeMap.navigableKeySet()) {
            System.out.println(i);
        }
        //只用values来取值是无法获取顺序值的
        for (Object i : treeMap.values()) {
            System.out.println(i);
        }
        System.out.println("-----");
        //根据key获得最小值和最大值
        System.out.println(treeMap.firstEntry());
        System.out.println(treeMap.lastEntry());
        //基本上没有根据value来排序的方法，所以treemap主要是根据key来决定排序
        System.out.println();
    }

    @Test
    public void test() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(3,1);
        linkedHashMap.put(2,2);
        linkedHashMap.put(10,10);
        for (Object i : linkedHashMap.keySet()) {
            System.out.println(i);
        }
        //根据key的插入顺序来进行遍历
        for (Object i : linkedHashMap.values()) {
            System.out.println(i);
        }
    }

}

//LRU缓存实现需要继承LinkedHashMap并重写removeEldestEntry
//所以必须在构造方法中设置初始容量，负载因子，以便确定缓存的空间大小
//public LRU(int initialCapacity,
//           float loadFactor,
//           boolean accessOrder) {
//    super(initialCapacity, loadFactor, accessOrder);
//}
class LRU<K,V> extends LinkedHashMap<K, V> implements Map<K, V> {

    private static final long serialVersionUID = 1L;

    public LRU(int initialCapacity,
               float loadFactor,
               boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * @description 重写LinkedHashMap中的removeEldestEntry方法，当LRU中元素多余6个时，
     *              删除最不经常使用的元素
     * @author rico
     * @created 2017年5月12日 上午11:32:51
     * @param eldest
     * @return
     * @see java.util.LinkedHashMap#removeEldestEntry(java.util.Map.Entry)
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        // TODO Auto-generated method stub
        if(size() > 6){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        LRU<Character, Integer> lru = new LRU<Character, Integer>(
                16, 0.75f, true);

        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}
