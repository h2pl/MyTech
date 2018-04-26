package com.javase.回调和事件.回调;


/**
 * Created by 周杰伦 on 2018/4/26.
 */
public class Tom implements Student{

    @Override
    public void resolveProblem(Teacher teacher) {
        try {
            //学生思考了3秒后得到了答案，通过老师提供的回调方法告诉老师。
            Thread.sleep(3000);
            System.out.println("work out");
            teacher.tellAnswer(111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
