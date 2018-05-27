package com.Java网络编程和NIO.NIO.客户端;

/**
 * Created by 周杰伦 on 2018/5/26.
 */
public class NIO非阻塞实现 {
    //线程需要自旋检查数据是否就绪，非常浪费资源，不能直接使用，
    //应该搭配socketchannel和选择器来实现IO多路复用的非阻塞模型。
    //此时只需要一个线程阻塞地进行选择即可，节省线程数量以及线程切换的开销。
}
