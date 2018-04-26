package com.javase.回调和事件.回调;

/**
 * Created by 周杰伦 on 2018/4/26.
 */
//学生的接口，解决问题的方法中要传入老师的引用，否则无法完成对具体实例的回调。
    //写为接口的好处就是，很多个学生都可以实现这个接口，并且老师在提问题时可以通过
    //传入List<Student>来聚合学生，十分方便。
public interface Student {
    void resolveProblem (Teacher teacher);
}
