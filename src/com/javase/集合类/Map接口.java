package com.javase.集合类;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by 周杰伦 on 2018/5/6.
 */
public class Map接口 {
    //Map接口是最上层接口，Map接口实现类必须实现put和get等哈希操作。
    //并且要提供keyset和values，以及entryset等查询结构。
    //public interface Map<K,V>
    class MyMap implements Map {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Object get(Object key) {
            return null;
        }

        @Override
        public Object put(Object key, Object value) {
            return null;
        }

        @Override
        public Object remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set keySet() {
            return null;
        }

        @Override
        public Collection values() {
            return null;
        }

        @Override
        public Set<Entry> entrySet() {
            return null;
        }
    }
}
