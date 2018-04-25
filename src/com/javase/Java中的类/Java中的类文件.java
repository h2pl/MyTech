package com.javase.Java中的类;
//import可以导入基础包以及公开的类，需要使用类名的全路径
//并且在导入某个包.*时，是不会把子包的类给导进来的，这样可以避免导入错误。
//注意
//import com.javase.Java中的类.一个包.包访问权限;
//这个导入会报错，因为这个类没有用public修饰，无法用import导入。
import com.javase.Java中的类.一个包.全局访问;//可以导入。
/**
 * Created by 周杰伦 on 2018/4/24.
 */
//Java中的文件名要和public修饰的类名相同，否则会报错
//如果没有public修饰的类，则文件可以随意命名
public class Java中的类文件 {

}

//非公共开类的访问权限默认是包访问权限，不能用private和protected
//一个外部类的访问权限只有两种，一种是包内可见，一种是包外可见。
//如果用private修饰，其他类根本无法看到这个类，也就没有意义了。
//如果用protected，虽然也是包内可见，但是如果有子类想要继承该类但是不同包时，
//压根找不到这个类，也不可能继承它了，所以干脆用default代替。
class A{

}
