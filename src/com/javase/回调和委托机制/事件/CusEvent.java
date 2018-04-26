package com.javase.回调和委托机制.事件;

import java.util.EventObject;

/**
 * Created by 周杰伦 on 2018/4/26.
 */
//事件类，Java中通过继承EventObject来实现，用于包装事件源，会传入事件源的引用，
// 以便在监听器监听到该事件时可以知道事件源发生了什么变化

public class CusEvent extends EventObject{

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    private static final long serialVersionUID = 1L;
    private Object source;//事件源

    public CusEvent(Object source){
        super(source);
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
