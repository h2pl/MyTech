package com.Java集合;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Created by 周杰伦 on 2018/5/8.
 */
public class LinkedList和Queue {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        Queue queue = new ArrayDeque();

        linkedList.add(1);
        Deque deque = new ArrayDeque();
        PriorityQueue queue1 = new PriorityQueue();
    }

    @Test
    public void test() {
        LinkedList linkedList = new LinkedList();
        //链表初始只有一个节点，不会自动扩容。
        linkedList.add(1);
        linkedList.addLast(2);
        linkedList.addFirst(0);

        //数组实现队列
        Queue queue = new ArrayDeque();
        queue.add(1);
        queue.offer(2);
        queue.offer(3);
        //队列先进先出，取的都是队头元素
        System.out.println(queue.peek());
        System.out.println(queue.element());
        System.out.println(queue.size());
        queue.poll();
        //虽然是数组实现，但是只允许取头尾元素，符合队列的性质
        System.out.println(queue.peek());

        //链表实现队列
        Queue queue1 = new LinkedList();
        queue1.offer(1);
        //和数组实现的队列类似
    }
}
