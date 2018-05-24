package com.Java并发.JUC.阻塞队列;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by 周杰伦 on 2018/5/24.
 */
public class 另外两个 {
    public static void main(String[] args) {
        //双端不加锁队列。cas操作
        ConcurrentLinkedDeque deque = new ConcurrentLinkedDeque();
        //转移队列，存数据但不加锁，实现和SynchonousQueue类似的功能
        TransferQueue queue = new LinkedTransferQueue();
    }
}
