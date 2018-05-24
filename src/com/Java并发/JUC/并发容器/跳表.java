package com.Java并发.JUC.并发容器;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by 周杰伦 on 2018/5/23.
 */
public class 跳表 {
    public static void main(String[] args) {
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();
        Set set = concurrentSkipListMap.entrySet();
    }
}
