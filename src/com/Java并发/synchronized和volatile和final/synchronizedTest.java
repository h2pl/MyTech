package com.Java并发.synchronized和volatile和final;

/**
 * Created by 周杰伦 on 2018/5/18.
 */
public class synchronizedTest {
    synchronizedTest s = new synchronizedTest();
    public synchronized void sync() {

    }
    public void test() {
        synchronized (s) {

        }
        synchronized (this) {

        }
        synchronized (this.getClass()) {

        }
        synchronized (synchronizedTest.class) {

        }
    }
}
