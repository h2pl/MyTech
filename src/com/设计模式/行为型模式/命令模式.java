package com.设计模式.行为型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 命令模式 {
}
//通用Receiver类
abstract class Receiver {
    public abstract void doSomething();
}

//具体Receiver类
class ConcreteReciver1 extends Receiver{
    //每个接收者都必须处理一定的业务逻辑
    public void doSomething(){ }
}
 class ConcreteReciver2 extends Receiver{
    //每个接收者都必须处理一定的业务逻辑
    public void doSomething(){ }
}

//抽象Command类
 abstract class Command {
    public abstract void execute();
}

//具体的Command类
 class ConcreteCommand1 extends Command {
    //对哪个Receiver类进行命令处理
    private Receiver receiver;
    //构造函数传递接收者
    public ConcreteCommand1(Receiver _receiver){
        this.receiver = _receiver;
    }

    //必须实现一个命令
    public void execute() {
        //业务处理
        this.receiver.doSomething();
    }
}

 class ConcreteCommand2 extends Command {
    //哪个Receiver类进行命令处理
    private Receiver receiver;
    //构造函数传递接收者
    public ConcreteCommand2(Receiver _receiver){
        this.receiver = _receiver;
    }
    //必须实现一个命令
    public void execute() {
        //业务处理
        this.receiver.doSomething();
    }
}

//调用者Invoker类
 class Invoker {
    private Command command;

    public void setCommand(Command _command){
        this.command = _command;
    }

    public void action() {
        this.command.execute();
    }
}

//场景类
class Client {
    public static void main(String[] args){
        Invoker invoker = new Invoker();
        Receiver receiver = new ConcreteReciver1();

        Command command = new ConcreteCommand1(receiver);
        invoker.setCommand(command);
        invoker.action();
    }
}