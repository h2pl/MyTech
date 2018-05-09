package com.Java集合;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 周杰伦 on 2018/5/8.
 */
public class Iterator和Comparable {

}
class FailFastTest {
    private static List<Integer> list = new ArrayList<>();

    /**
     * @desc:线程one迭代list
     * @Project:test
     * @file:FailFastTest.java
     * @Authro:chenssy
     * @data:2014年7月26日
     */
    private static class threadOne extends Thread{
        public void run() {
            Iterator<Integer> iterator = list.iterator();
            while(iterator.hasNext()){
                int i = iterator.next();
                System.out.println("ThreadOne 遍历:" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @desc:当i == 3时，修改list
     * @Project:test
     * @file:FailFastTest.java
     * @Authro:chenssy
     * @data:2014年7月26日
     */
    private static class threadTwo extends Thread{
        public void run(){
            int i = 0 ;
            while(i < 6){
                System.out.println("ThreadTwo run：" + i);
                if(i == 3){
                    list.remove(i);
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 10;i++){
            list.add(i);
        }
        new threadOne().start();
        new threadTwo().start();
    }
}

class BookBean implements Serializable, Comparable {
    private String name;
    private int count;


    public BookBean(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 重写 equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookBean)) return false;

        BookBean bean = (BookBean) o;

        if (getCount() != bean.getCount()) return false;
        return getName().equals(bean.getName());

    }

    /**
     * 重写 hashCode 的计算方法
     * 根据所有属性进行 迭代计算，避免重复
     * 计算 hashCode 时 计算因子 31 见得很多，是一个质数，不能再被除
     * @return
     */
    @Override
    public int hashCode() {
        //调用 String 的 hashCode(), 唯一表示一个字符串内容
        int result = getName().hashCode();
        //乘以 31, 再加上 count
        result = 31 * result + getCount();
        return result;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    /**
     * 当向 TreeSet 中添加 BookBean 时，会调用这个方法进行排序
     * @param another
     * @return
     */
    @Override
    public int compareTo(Object another) {
        if (another instanceof BookBean){
            BookBean anotherBook = (BookBean) another;
            int result;

            //比如这里按照书价排序
            result = getCount() - anotherBook.getCount();

            //或者按照 String 的比较顺序
            //result = getName().compareTo(anotherBook.getName());

            if (result == 0){   //当书价一致时，再对比书名。 保证所有属性比较一遍
                result = getName().compareTo(anotherBook.getName());
            }
            return result;
        }
        // 一样就返回 0
        return 0;
    }
}
