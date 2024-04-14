



### java锁

##### 死锁、乐观锁、悲观锁 （常问）
死锁： 就是多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。
乐观锁： 
>总是假设最好的情况，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是 在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号机制和 CAS 算法实现。乐观锁适用于多读的应用类型，这样可以提高吞吐量


悲观锁： 
>总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时 候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁（共享资源每次只给一个线程使 用，其它线程阻塞，用完后再把资源转让给其它线程）。传统的关系型数据库里边就用到了 很多这种锁机制，比如行锁，表锁等，都是在做操作之前先上锁。Java 中 synchronized 和 ReentrantLock 等独占锁就是悲观锁思想的实现。


##### 说一下 synchronized 底层实现原理？（高薪常问）
synchronized 可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进 入到临界区，同时它还可以保证共享变量的内存可见性。 Java 中每一个对象都可以作为锁，这是 synchronized 实现同步的基础：
 普通同步方法，锁是当前实例对象
 静态同步方法，锁是当前类的 class 对象
 同步方法块，锁是括号里面的对象


##### synchronized 和 volatile 的区别是什么？（高薪常问）
* 一个可见性一个阻塞线程
volatile 本质是在告诉 jvm 当前变量在寄存器（工作内存）中的值是不确定的，需要从 主存中读取；但没有阻塞其他线程
synchronized 则是锁定当前变量，只有当前线程可以访问该变量，其他线程被阻塞住。

* 级别
volatile 仅能使用在变量级别；
synchronized 则可以使用在变量、方法、和类级别的。 

##### synchronized 和 Lock 有什么区别？ （高薪常问） 
synchronized 会自动释放锁(a 线程执行完同步代码会释放锁 ；b 线程执行过程中发生异常会释放锁)，
Lock 需在 finally 中手工释放锁（unlock()方法释放锁），否则容易造成线程死锁；

##### 1.9什么是单例模式?有几种?必会

单例模式：某个类的实例在多线程环境下只会被创建一次出来。

单例模式有饿汉式单例模式、懒汉式单例模式和双检锁单例模式三种。

饿汉式：<font color=#66CC99 style=" font-weight:bold;">线程安全，一开始就初始化</font>。
在线程访问单例对象之前就已经创建好一个私有静态全局对象。再加上，由于一个<font color=#66CC99 style=" font-weight:bold;">类在整个生命周期中只会被加载一次</font>，因此该单例类只会创建一个实例。线程每次都只能也必定只可以拿到这个唯一的对象
```java
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {
        // 私有构造方法，防止外部实例化
    }

    public static Singleton getInstance() {
        return instance;
    }
}
```
懒汉式：<font color=#66CC99 style=" font-weight:bold;">非线程安全，延迟初始化</font>。
```java
if(instance == null){
	//多线程情况下,多个线程同时检查instance=null的情况下会创建多个实例
	instance = new Singleton();
}
```

双检锁：线程安全，延迟初始化。
给该类加锁后,该段代码只能被一个线程访问
```java
private static volatile Singleton instance;
synchroized(Singleton.class){
	
	if(singleton == null){
		singleton = new Singleton();
	}
}
```


### java集合

##### List 和 Map、Set 的区别（必会）
List 和 Set 是存储单列数据的集合，Map 是存储键值对这样的双列数据的集合；
List 中存储的数据是按照插入的顺序排列的
Map 中存储是无序的键值对， 
Set 中存储的数据是无顺序的，不重复，但元素在集合中的位置是由元素 的 hashcode 决定，即位置是固定的

##### List 和 Map、Set 的实现类（必会)
![](img/Pasted%20image%2020240414134114.png)


##### Hashmap 的底层原理（高薪常问）
Jdk8 数组+链表或者数组+红黑树实现，当链表中的元素超过了 8 个以后， 会 将链表转换为红黑树，当红黑树节点 小于 等于 6 时又会退化为链表。

##### java多线程

##### 创建线程有几种方式（必会） 
1.继承 Thread 类并重写 run 方法创建线程，实现简单但不可以继承其他类 
2.实现 Runnable 接口并重写 run 方法。避免了单继承局限性 
3..实现 Callable 接口并重写 call 方法，创建线程。可以获取线程执行结果的返回值
4.使用线程池创建

##### 3.4 如何启动一个新线程、调用 start 和 run 方法的区别？（必会)
run方法中写线程的逻辑,
是调用start方法开启线程后,让jvm去调用run方法的

##### 线程有哪几种状态以及各种状态之间的转换？(必会
new创建线程,
start开启线程,让线程进入就绪状态,
当线程获取到cpu后就运行run中的代码,进入运行状态
然后就进入阻塞,死亡,或者重新就绪

阻塞:
* 等待:
	wait等待其他线程执行完
* 超时等待
	sleep() 或 join()或发出了 I/O 请求
* 同步阻塞
	线程在获取 synchronized 同步锁失败(因为锁被其它线程所占用

死亡:
线程执行完了或者因异常退出了 run()方法

##### wait()和 sleep()的区别？（必会）
关于锁的释放： 
wait():在等待的过程中会释放锁； 
sleep():在等待的过程中不会释放锁 

使用的范围： 
wait():必须在同步代码块中使用； 
sleep():可以在任何地方使用； 


### java其他

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


##### 1.11 jdk1.8 的新特性（常问）

1. Lambda表达式：Lambda 表达式是 Java 8 中引入的功能，可以简洁地实现函数式编程。

2. Stream API：Stream API 提供了一种更容易、更高效处理集合数据的方式，支持各种操作如过滤、映射、归约等。

3. CompletableFuture 类：CompletableFuture 类提供了更强大的异步编程支持，可以更容易地实现并发操作。



##### 1.13字节流和字符流哪个好？怎么选择？
字节流继承inputStream和OutputStream
字符流继承自InputSteamReader和OutputStreamWriter

<font color=#99CCFF style=" font-weight:bold;">主要区别在是否经过缓冲区</font>
字节流直接对于二进制数据进行io操作,不经过缓冲区,而字符流需要经过缓冲区,所以
* 字符流适合处理内存中需要频繁处理的数据
* 字节流不经过缓冲区适合大型文件的io操作


##### 1.13.2BIO、NIO、AIO 有什么区别？（高薪常问）

BIO就是传统的阻塞式io,一个读取完了,等待另一个读取,
nio就是一个线程可以处理多个通道的io操作,实现多路复用,不用每个io连接让独立的线程去处理
Aio就是异步io,通过事件驱动和回调函数来处理io连接, 相较于nio不需要主动轮询了




##### 1.14ThreadLocal 的原理（高薪常问）

<font color=#99CCFF style=" font-weight:bold;">不会</font>
ThreadLocal：为共享变量在每个线程中创建一个副本，每个线程都可以访问自己
内部的副本变量。通过 threadlocal 保证线程的安全性。其实在 ThreadLocal 类中有一个静态内部类 ThreadLocalMap(其类似于 Map)，用键值对的形式存储每一个线程的变量副本，ThreadLocalMap 中元素的 key 为当前ThreadLocal 对象，而 value 对应线程的变量副本。
ThreadLocal 本身并不存储值，它只是作为一个 key 保存到 ThreadLocalMap中，但是这里要注意的是它作为一个 key 用的是弱引用，因为没有强引用链，弱引用在 GC的时候可能会被回收。这样就会在 ThreadLocalMap 中存在一些 key 为 null 的键值对（Entry）。因为 key 变成 null 了，我们是没法访问这些 Entry 的，但是这些 Entry 本身是不会被清除的。如果没有手动删除对应 key 就会导致这块内存即不会回收也无法访问，也就是内存泄漏。
使用完 ThreadLocal 之后，记得调用 remove 方法。 在不使用线程池的前提下，
即使不调用 remove 方法，线程的"变量副本"也会被 gc 回收，即不会造成内存泄漏的情况。


