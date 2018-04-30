package com.javase.Class和Object.Object方法.用到的类;

/**
 * Created by 周杰伦 on 2018/4/30.
 */
public class equals和hashcode方法 {
    @Override
    //修改equals时必须同时修改hashcode方法，否则在作为key时会出问题
    public boolean equals(Object obj) {
        return (this == obj);
    }

    @Override
    //相同的对象必须有相同hashcode，不同对象可能有相同hashcode
    public int hashCode() {
        return hashCode() >> 2;
    }
}
