package com.javase.回调和委托机制.事件;

import java.util.EventListener;

/**
 * Created by 周杰伦 on 2018/4/26.
 */
///监听器用于监听事件，事件源发生变化后，会自动生成事件并且调用监听器的回调方法，
    //在调用过程中将包含事件源引用的事件传给监听器，监听器根据事件获得其内部封装的事件源
    //并且获取到事件源的具体改变。
public class CusEventListener implements EventListener {

    //事件发生后的回调方法
    public void fireCusEvent(CusEvent e){
        EventSourceObject eObject = (EventSourceObject)e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named \""+eObject.getName()+"\"");    }
}
