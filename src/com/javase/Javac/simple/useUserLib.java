
//D:\MyTech\src\com\javase\Javac>javac useUserLib.java
//        useUserLib.java:2: 错误: 程序包com.javase.Javac.userlib不存在
//import com.javase.Javac.userlib.tool;

//使用javac编译时，由于只会查环境变量配置的classpath，所以找不到其他包下的class文件
//此时必须用-classpath来指定classpath，ide中由于使用的项目的编译路径
//同一个项目或者模块下的class文件按照包分类的方式存放，通过这种方式使包中类能互相访问。

//在D:\MyTech\src\com\javase\Javac> 使用javac -encoding UTF-8 -classpath userlib useUserLib.java
//可以正常编译
//public class useUserLib {
//	tool t = new tool();
//}
