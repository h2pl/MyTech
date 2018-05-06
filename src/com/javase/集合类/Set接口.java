package com.javase.集合类;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class Set接口 {
    // Set接口规定将set看成一个集合，并且使用和数组类似的增删改查方式，同时提供iterator迭代器
    //    public interface Set<E> extends Collection<E>
    //    public interface Collection<E> extends Iterable<E>
    //    public interface Iterable<T>
    class MySet implements Set {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }
    }
}
