package com.设计模式.行为型模式;

/**
 * Created by 周杰伦 on 2018/5/25.
 */
public class 状态模式 {
    public static void main(String[] args) {
        // 我们需要操作的是 iPhone X
        Product product = new Product();

        // 看看怎么进行补库存操作
        State revertState = new RevertState();
        revertState.doAction(product);

        // 同样的，减库存操作也非常简单
        State deductState = new DeductState();
        deductState.doAction(product);

        // 如果需要我们可以获取当前的状态
        // context.getState().toString();

    }
}
class Product{

    private State state;

    public void setState(State state) {
        this.state = state;
    }
}
interface State {
    public void doAction(Product context);
}
class DeductState implements State {


    public void doAction(Product product) {
        System.out.println("商品卖出，准备减库存");
        product.setState(this);

        //... 执行减库存的具体操作
    }

    public String toString(){
        return "Deduct State";
    }
}
class RevertState implements State {
    public void doAction(Product product) {
        System.out.println("给此商品补库存");
        product.setState(this);

        //... 执行加库存的具体操作
    }
    public String toString() {
        return "Revert State";
    }
}
