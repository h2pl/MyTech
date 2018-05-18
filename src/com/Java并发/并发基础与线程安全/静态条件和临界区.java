package com.Java并发.并发基础与线程安全;

/**
 * Created by 周杰伦 on 2018/5/16.
 */
public class 静态条件和临界区 {
    //存在数据竞争的区域叫临界区，add方法是静态条件
    public class Counter {

        protected long count = 0;

        public void add(long value) {

            this.count = this.count + value;

        }

    }

}
