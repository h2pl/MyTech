package com.javase.集合类;

import java.util.Iterator;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class Iterator接口 {
    //iterable实现类一般是集合，实现该接口意味着可以提供迭代器来执行迭代查询
    class MyIterable implements Iterable {

        @Override
        public Iterator iterator() {
            return null;
        }
    }
    //因为上面说的实现类需要实现一个迭代器，所以就有了Iterator接口。
    //集合类在自身内部实现一个迭代器，提供给外界进行访问
    class MyIterator implements Iterator {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
