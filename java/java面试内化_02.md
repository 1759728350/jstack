
##### 1.9什么是单例模式?有几种?必会

单例模式：某个类的实例在多线程环境下只会被创建一次出来。

单例模式有饿汉式单例模式、懒汉式单例模式和双检锁单例模式三种。

饿汉式：<font color=#66CC99 style=" font-weight:bold;">线程安全，一开始就初始化</font>。
在线程访问单例对象之前就已经创建好一个私有静态全局对象。再加上，由于一个<font color=#66CC99 style=" font-weight:bold;">类在整个生命周期中只会被加载一次</font>，因此该单例类只会创建一个实例。线程每次都只能也必定只可以拿到这个唯一的对象
![](img/Pasted%20image%2020220802234025.png)
懒汉式：<font color=#66CC99 style=" font-weight:bold;">非线程安全，延迟初始化</font>。
![](img/Pasted%20image%2020220802234059.png)
```java
if(instance == null){
	//多线程情况下,多个线程同时检查instance=null的情况下会创建多个实例
	instance = new Singleton();
}
```

双检锁：线程安全，延迟初始化。
给该类加锁后,该段代码只能被一个线程访问
```java
synchroized(Singleton.class){
	
	if(singleton == null){
		singleton = new Singleton();
	}
}
```
![](img/Pasted%20image%2020220802234113.png)


##### 1.10反射（了解）

在 Java 中的反射机制是指在运行状态中，对于任意一个类都能够知道这个类所有的
属性和方法；并且对于任意一个对象，都能够调用它的任意一个方法；这种动态获
取信息以及动态调用对象方法的功能成为 Java 语言的反射机制。

获取 Class 对象的 3 种方法 ：
```java
//调用某个对象的 getClass()方法

Person p=new Person();

Class clazz=p.getClass();

//调用某个类的 class 属性来获取该类对应的 Class 对象

Class clazz=Person.class;

//使用 Class 类中的 forName()静态方法(最安全/性能最好)

Class clazz=Class.forName("类的全路径"); (最常用)
```


##### 1.11 jdk1.8 的新特性（高薪常问）
<font color=#FFCCCC style=" font-weight:bold;">暂时略过</font>

1 Lambda 表达式
Lambda 允许<font color=#66CC99 style=" font-weight:bold;">把函数作为一个方法的参数</font>。

可以取代大部分的匿名内部类，写出更优雅的Java 代码，尤其在集合的遍历和其他集合操作中，可以极大地优化代码结构。
```java
	//详细看如何创建一个线程
	new Thread(()->System.out.println("这是个方法")).start();
```
[四种线程创建方式](https://blog.csdn.net/HuZeyo/article/details/120315914?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165943744416780366594397%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165943744416780366594397&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-120315914-null-null.142^v38^pc_rank_34&utm_term=java%E5%88%9B%E5%BB%BA%E4%B8%80%E4%B8%AA%E7%BA%BF%E7%A8%8B&spm=1018.2226.3001.4187)
[lambda表达式详细使用](https://blog.csdn.net/kangkang12221222/article/details/122651474?ops_request_misc=&request_id=&biz_id=102&utm_term=java%20lam&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-4-122651474.142^v38^pc_rank_34&spm=1018.2226.3001.4187)

2 方法引用

方法引用允许直接引用已有 Java 类或对象的方法或构造方法。(没学会)



==============================================================




##### 1.13字节流和字符流哪个好？怎么选择？
字节流继承inputStream和OutputStream
字符流继承自InputSteamReader和OutputStreamWriter

缓冲区方面:
字节流直接对于二进制数据进行io操作,不经过缓冲区,而字符流需要经过缓冲区,所以字符流对于内存中需要频繁处理的数据/字符串具有更强的读写能力减少资源浪费(缓冲区满了再发送),也因此字节流不用缓冲区适合大型文件的io操作


##### 1.13.2BIO、NIO、AIO 有什么区别？（高薪常问）

<font color=#99CCFF style=" font-weight:bold;">不会</font>


[io面试](https://blog.csdn.net/weixin_44196561/article/details/120261292?ops_request_misc=&request_id=&biz_id=102&utm_term=java%20io%E9%9D%A2%E8%AF%95%E9%A2%98&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-120261292.nonecase&spm=1018.2226.3001.4187)


##### 1.14ThreadLocal 的原理（高薪常问）

<font color=#99CCFF style=" font-weight:bold;">不会</font>
ThreadLocal：为共享变量在每个线程中创建一个副本，每个线程都可以访问自己
内部的副本变量。通过 threadlocal 保证线程的安全性。其实在 ThreadLocal 类中有一个静态内部类 ThreadLocalMap(其类似于 Map)，用键值对的形式存储每一个线程的变量副本，ThreadLocalMap 中元素的 key 为当前ThreadLocal 对象，而 value 对应线程的变量副本。
ThreadLocal 本身并不存储值，它只是作为一个 key 保存到 ThreadLocalMap中，但是这里要注意的是它作为一个 key 用的是弱引用，因为没有强引用链，弱引用在 GC的时候可能会被回收。这样就会在 ThreadLocalMap 中存在一些 key 为 null 的键值对（Entry）。因为 key 变成 null 了，我们是没法访问这些 Entry 的，但是这些 Entry 本身是不会被清除的。如果没有手动删除对应 key 就会导致这块内存即不会回收也无法访问，也就是内存泄漏。
使用完 ThreadLocal 之后，记得调用 remove 方法。 在不使用线程池的前提下，
即使不调用 remove 方法，线程的"变量副本"也会被 gc 回收，即不会造成内存泄漏的情况。



计算机网络

计算机组成原理

操作系统

python

数据结构
