package com.javase.Java中的类.内部类;

import org.junit.jupiter.api.Test;

/**
 * Created by 周杰伦 on 2018/4/25.
 */
public class 内部类的继承 {
    @Test
    public void main() {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
    class WithInner {
        class Inner {
            Inner(){
                System.out.println("this is a constructor in WithInner.Inner");
            };
        }
    }

    public class InheritInner extends WithInner.Inner {
        // ! InheritInner() {} // Won't compile
        InheritInner(WithInner wi) {
            wi.super();
            System.out.println("this is a constructor in InheritInner");
        }
    }
}
