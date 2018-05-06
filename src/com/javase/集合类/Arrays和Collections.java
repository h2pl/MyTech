package com.javase.集合类;

import java.util.*;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class Arrays和Collections {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        //排序，二分查找，逆序，获取线程安全的集合实例
        Collections.sort(list);
        Collections.sort(list, comparator);
        Collections.binarySearch(list,1);
        Collections.reverse(list);
        Collections.synchronizedList(list);

        Object []arr = new Object[10];
        //排序，二分查找，填充集合，获取集合流，并行排序。
        Arrays.sort(arr);
        Arrays.binarySearch(arr,1);
        Arrays.fill(arr,1);
        Arrays.stream(arr);
        Arrays.parallelSort(arr,comparator);


    }
}
