package com.javase.Class和Object;

/**
 * Created by 周杰伦 on 2018/4/28.
 */
public class Object类 {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

//    public final native Class<?> getClass();

//    public final void wait() throws InterruptedException {
//        wait(0);
//    }
//    public final native void wait(long timeout) throws InterruptedException;


//    public final native void notify();

    protected void finalize() throws Throwable { }


}
