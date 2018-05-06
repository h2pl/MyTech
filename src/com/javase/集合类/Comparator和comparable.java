package com.javase.集合类;

import java.util.Comparator;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class Comparator和comparable {
    //实现该接口的类通过内部方法compareTo进行比较
    class MyCom implements Comparable {

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }
    //实现该接口只是实现了一个比较器，这个比较器可以用在多个地方，
    //只需要比较类型和比较器里的类型不冲突即可，所以集合类排序时可以传入一个比较器，通过比较器定义的规则来进行比较
    class Comp implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }
    }
}
