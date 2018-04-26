package com.javase.回调和委托机制.回调;


/**
 * Created by 周杰伦 on 2018/4/26.
 */
public class Test {
    public static void main(String[] args) {
        //测试
        Student tom = new Tom();
        Teacher lee = new Teacher(tom);
        lee.askProblem(tom, lee);
        //结果
//        等学生回答问题的时候老师玩了 1秒的手机
//        等学生回答问题的时候老师玩了 2秒的手机
//        等学生回答问题的时候老师玩了 3秒的手机
//        work out
//        the answer is 111
    }
}
