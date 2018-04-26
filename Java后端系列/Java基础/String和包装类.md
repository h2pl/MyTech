---
title: Java基础3：深入理解String及包装类
date: 2018-04-23 11:35:30
tags:
	- Java基础
categories:
	- 后端
	- Java
---
本节主要介绍字符串类型和相关包装类的使用和原理。

具体代码在我的GitHub中可以找到
> https://github.com/h2pl/MyTech

文章首发于我的个人博客：
> https://h2pl.github.io/2018/04/23/javase3

更多关于Java后端学习的内容请到我的CSDN博客上查看：

https://blog.csdn.net/a724888
<!-- more -->

## String的连接

    @Test
    public void contact () {
        //1连接方式
        String s1 = "a";
        String s2 = "a";
        String s3 = "a" + s2;
        String s4 = "a" + "a";
        String s5 = s1 + s2;
        //表达式只有常量时，编译期完成计算
        //表达式有变量时，运行期才计算，所以地址不一样
        System.out.println(s3 == s4); //f
        System.out.println(s3 == s5); //f
        System.out.println(s4 == "aa"); //t

    }
    
## String类型的intern
    public void intern () {
        //2：string的intern使用
        //s1是基本类型，比较值。s2是string实例，比较实例地址
        //字符串类型用equals方法比较时只会比较值
        String s1 = "a";
        String s2 = new String("a");
        //调用intern时,如果s2中的字符不在常量池，则加入常量池并返回常量的引用
        String s3 = s2.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
    
## String类型的equals

    //字符串的equals方法
    //    public boolean equals(Object anObject) {
    //            if (this == anObject) {
    //                return true;
    //            }
    //            if (anObject instanceof String) {
    //                String anotherString = (String)anObject;
    //                int n = value.length;
    //                if (n == anotherString.value.length) {
    //                    char v1[] = value;
    //                    char v2[] = anotherString.value;
    //                    int i = 0;
    //                    while (n-- != 0) {
    //                        if (v1[i] != v2[i])
    //                            return false;
    //                        i++;
    //                    }
    //                    return true;
    //                }
    //            }
    //            return false;
    //        }
    
## StringBuffer和Stringbuilder
底层是继承父类的可变字符数组value

    /**
     * The value is used for character storage.
     */
    char[] value;
    初始化容量为16
    
    /**
     * Constructs a string builder with no characters in it and an
     * initial capacity of 16 characters.
     */
    public StringBuilder() {
        super(16);
    }
    这两个类的append方法都是来自父类AbstractStringBuilder的方法
    
    public AbstractStringBuilder append(String str) {
        if (str == null)
            return appendNull();
        int len = str.length();
        ensureCapacityInternal(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }
    @Override
    public StringBuilder append(String str) {
        super.append(str);
        return this;
    }
    
    @Override
    public synchronized StringBuffer append(String str) {
        toStringCache = null;
        super.append(str);
        return this;
    }
### append

    Stringbuffer在大部分涉及字符串修改的操作上加了synchronized关键字来保证线程安全，效率较低。
    
    String类型在使用 + 运算符例如
    
    String a = "a"
    
    a = a + a;时，实际上先把a封装成stringbuilder，调用append方法后再用tostring返回，所以当大量使用字符串加法时，会大量地生成stringbuilder实例，这是十分浪费的，这种时候应该用stringbuilder来代替string。



### 扩容

    #注意在append方法中调用到了一个函数
    
    ensureCapacityInternal(count + len);
    该方法是计算append之后的空间是否足够，不足的话需要进行扩容
    
    public void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity > 0)
            ensureCapacityInternal(minimumCapacity);
    }
    private void ensureCapacityInternal(int minimumCapacity) {
        // overflow-conscious code
        if (minimumCapacity - value.length > 0) {
            value = Arrays.copyOf(value,
                    newCapacity(minimumCapacity));
        }
    }
    如果新字符串长度大于value数组长度则进行扩容
    
    扩容后的长度一般为原来的两倍 + 2；
    
    假如扩容后的长度超过了jvm支持的最大数组长度MAX_ARRAY_SIZE。
    
    考虑两种情况
    
    如果新的字符串长度超过int最大值，则抛出异常，否则直接使用数组最大长度作为新数组的长度。
    
    private int hugeCapacity(int minCapacity) {
        if (Integer.MAX_VALUE - minCapacity < 0) { // overflow
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE)
            ? minCapacity : MAX_ARRAY_SIZE;
    }
### 删除


    这两个类型的删除操作：
    
    都是调用父类的delete方法进行删除
    
    public AbstractStringBuilder delete(int start, int end) {
        if (start < 0)
            throw new StringIndexOutOfBoundsException(start);
        if (end > count)
            end = count;
        if (start > end)
            throw new StringIndexOutOfBoundsException();
        int len = end - start;
        if (len > 0) {
            System.arraycopy(value, start+len, value, start, count-end);
            count -= len;
        }
        return this;
    }
    事实上是将剩余的字符重新拷贝到字符数组value。

这里用到了system.arraycopy来拷贝数组，速度是比较快的

## system.arraycopy方法

    转自知乎：
    
    在主流高性能的JVM上（HotSpot VM系、IBM J9 VM系、JRockit系等等），可以认为System.arraycopy()在拷贝数组时是可靠高效的——如果发现不够高效的情况，请报告performance bug，肯定很快就会得到改进。
    
    java.lang.System.arraycopy()方法在Java代码里声明为一个native方法。所以最naïve的实现方式就是通过JNI调用JVM里的native代码来实现。




## String的不可变性
关于String的不可变性，这里转一个不错的回答

### 什么是不可变？ 
    
String不可变很简单，如下图，给一个已有字符串"abcd"第二次赋值成"abcedl"，不是在原内存地址上修改数据，而是重新指向一个新对象，新地址。
![image](https://pic1.zhimg.com/80/46c03ae5abf6111879423f38375207cc_hd.jpg)     
      
### String为什么不可变？  

翻开JDK源码，java.lang.String类起手前三行，是这样写的：
    
    public final class String implements java.io.Serializable, Comparable<String>, CharSequence {   
      /** String本质是个char数组. 而且用final关键字修饰.*/     
    private final char value[];  ...  ...
     } 
     
首先String类是用final关键字修饰，这说明String不可继承。再看下面，String类的主力成员字段value是个char[]数组，而且是用final修饰的。

final修饰的字段创建以后就不可改变。  有的人以为故事就这样完了，其实没有。因为虽然value是不可变，也只是value这个引用地址不可变。挡不住Array数组是可变的事实。
    
    
Array的数据结构看下图。
![image](https://pic2.zhimg.com/80/356d116d3fd43b622fc9721d399f5631_hd.jpg)    

也就是说Array变量只是stack上的一个引用，数组的本体结构在heap堆。

String类里的value用final修饰，只是说stack里的这个叫value的引用地址不可变。没有说堆里array本身数据不可变。看下面这个例子，  

    final int[] value={1,2,3} ；
    int[] another={4,5,6};
     value=another;    //编译器报错，final不可变 value用final修饰，编译器不允许我把value指向堆区另一个地址。
    但如果我直接对数组元素动手，分分钟搞定。
    
     final int[] value={1,2,3};
     value[2]=100;  //这时候数组里已经是{1,2,100}   所以String是不可变，关键是因为SUN公司的工程师。
     在后面所有String的方法里很小心的没有去动Array里的元素，没有暴露内部成员字段。
    
    private final char value[]这一句里，private的私有访问权限的作用都比final大。而且设计师还很小心地把整个String设成final禁止继承，避免被其他人继承后破坏。所以String是不可变的关键都在底层的实现，而不是一个final。考验的是工程师构造数据类型，封装数据的功力。 

### 不可变有什么好处？  
    
这个最简单地原因，就是为了安全。看下面这个场景（有评论反应例子不够清楚，现在完整地写出来），一个函数appendStr( )在不可变的String参数后面加上一段“bbb”后返回。appendSb( )负责在可变的StringBuilder后面加“bbb”。

总结以下String的不可变性。

> 1 首先final修饰的类只保证不能被继承，并且该类的对象在堆内存中的地址不会被改变。
> 
> 2 但是持有String对象的引用本身是可以改变的，比如他可以指向其他的对象。

> 3 final修饰的char数组保证了char数组的引用不可变。但是可以通过char[0] = 'a'来修改值。不过String内部并不提供方法来完成这一操作，所以String的不可变也是基于代码封装和访问控制的。
    
举个例子
    
    final class Fi {
        int a;
        final int b = 0;
        Integer s;
    
    }
    final char[]a = {'a'};
    final int[]b = {1};
    @Test
    public void final修饰类() {
        //引用没有被final修饰，所以是可变的。
        //final只修饰了Fi类型，即Fi实例化的对象在堆中内存地址是不可变的。
        //虽然内存地址不可变，但是可以对内部的数据做改变。
        Fi f = new Fi();
        f.a = 1;
        System.out.println(f);
        f.a = 2;
        System.out.println(f);
        //改变实例中的值并不改变内存地址。
    
    
        Fi ff = f;
        //让引用指向新的Fi对象，原来的f对象由新的引用ff持有。
        //引用的指向改变也不会改变原来对象的地址
        f = new Fi();
        System.out.println(f);
        System.out.println(ff);
    }
    这里的对f.a的修改可以理解为char[0] = 'a'这样的操作。只改变数据值，不改变内存值。



有关常量池和intern的内容在上一节讲到了。

具体参考：https://blog.csdn.net/a724888/article/details/80041698

下一节重讲一下final关键字。

具体参考：https://blog.csdn.net/a724888/article/details/80045107