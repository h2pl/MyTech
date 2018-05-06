package com.javase.集合类;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class Queue接口 {
    //queue接口是对队列的一个实现，需要提供队列的进队出队等方法。一般使用linkedlist作为实现类
    class MyQueue implements Queue {

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
        public Object[] toArray(Object[] a) {
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
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public boolean offer(Object o) {
            return false;
        }

        @Override
        public Object remove() {
            return null;
        }

        @Override
        public Object poll() {
            return null;
        }

        @Override
        public Object element() {
            return null;
        }

        @Override
        public Object peek() {
            return null;
        }
    }
}
