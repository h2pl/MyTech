package com.javase.回调和事件.事件;

/**
 * Created by 周杰伦 on 2018/4/26.
 */
public class MainTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventSourceObject object = new EventSourceObject();
        //注册监听器
        object.addCusListener(new CusEventListener(){
            //回调方法，由事件源生成事件并且回调。
            @Override
            public void fireCusEvent(CusEvent e) {
                super.fireCusEvent(e);
            }
        });
        //触发事件，实际应用中可能是通过鼠标点击，键盘敲击来触发事件。
        object.setName("eric");
    }
}
