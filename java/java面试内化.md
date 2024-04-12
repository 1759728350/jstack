

## 1.Java 基础 知识
##### 1.1面向对象(了解)

封装
就是把对象的属性和方法结合为一个整体，并尽可能<font color=#66CC99 style=" font-weight:bold;">隐藏内部实现细节</font>，就是把不想告诉或者不该告诉别人的东西隐藏起来，把可以告诉别人的公开，别人<font color=#66CC99 style=" font-weight:bold;">只能用我暴露的功能</font>实现需求，而不知道是如何实现的。增加安全性。

继承：子类继承父类的属性和方法，并能根据自己的需求扩展出新的行为，提高了代码的复用性。

多态是指不同的子类在继承父类后分别都重写覆盖了父类的方法，即<font color=#66CC99 style=" font-weight:bold;">父类同一个方法，在继承的子类中表现出不同的形式</font>。
,把不同的子类对象都当作父类来看，向上<font color=#66CC99 style=" font-weight:bold;">屏蔽了不同子类对象之间的差异</font>，写出通用的代码，以适应需求的不断变化。
```java
父类或接口  起的名字 = new 子类
(左边)              (右边)
```

抽象
抽象类用 abstract 关键字来修饰，用 abstract修饰类时，<font color=#66CC99 style=" font-weight:bold;">此类就不能被实例化</font>，从这里可以看出，抽象类（接口）就是为了继承而存在的

##### 1.2数据类型(了解)


![](img/Pasted%20image%2020220801191718.png)


##### 1.3JDK JRE JVM 的区别 （必会）

JVM 是 Java Virtual Machine（Java 虚拟机），
所有的java程序会首先被编译为.class的类文件，这种<font color=#66CC99 style=" font-weight:bold;">.class文件可以在虚拟机上执行</font>,是整个 java 实现<font color=#66CC99 style=" font-weight:bold;">跨平台</font>的最核心的部分，能够运行以 Java 语言的软件程序。

JRE（Java Runtime Environment）是运行 JAVA 程序所必须的环境的集合，
包含<font color=#66CC99 style=" font-weight:bold;"> java 虚拟机</font>和 java 程序的一些<font color=#66CC99 style=" font-weight:bold;">核心类库</font>。

JDK（Java Development Kit）是整个 Java 的核心，是 java 开发工具包，
包括了 Java 运行环境 JRE、Java 工具和 <font color=#66CC99 style=" font-weight:bold;">Java 基础类库</font>。

在JDK的安装目录下有一个jre目录，里面有两个文件夹bin和lib，在这里可以认为bin里的就是jvm，lib中则是jvm工作所需要的类库，而jvm和 lib合起来就称为jre。

[相关解释](https://blog.csdn.net/weixin_45525272/article/details/117825058?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165934959216782388031797%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165934959216782388031797&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-117825058-null-null.142^v37^pc_rank_34&utm_term=jre&spm=1018.2226.3001.4187)


##### 1.3.2在 Java 中，为什么不允许从静态方法中访问非静态变量？
静态变量属于类本身，<font color=#66CC99 style=" font-weight:bold;">在类加载的时候就会分配内存</font>，可以通过类名直接访问；
非静态变量属于类的对象，<font color=#66CC99 style=" font-weight:bold;">只有在类的对象产生时</font>，才会分配内存，通过类的实例去访问；
静态方法也属于类本身，但是此时没有类的实例，内存中没有非静态变量，所以无法调用。


##### 1.4重载和重写的区别（必会）
重载： 发生在同一个类中，方法名必须相同，参数类型不同.个数不同.顺序不同,方法返回
值和访问修饰符可以不同，发生在编译时。

重写： 发生在父子类中，方法名.参数列表必须相同，返回值范围小于等于父类，抛出的异
常范围小于等于父类，<font color=#66CC99 style=" font-weight:bold;">访问修饰符范围大于等于父类</font>；
如果父类方法访问修饰符为 <font color=#66CC99 style=" font-weight:bold;">private 则子类就不能重写该方法</font>。


##### 1.4.2final 在 java 中有什么作用？

-   final 修饰的类叫最终类，该类不能被<font color=#66CC99 style=" font-weight:bold;">继承</font>。
    
-   final 修饰的方法不能被<font color=#66CC99 style=" font-weight:bold;">重写</font>。
    
-   final 修饰的变量叫常量，常量<font color=#66CC99 style=" font-weight:bold;">必须初始化</font>，初始化之后值就不能被<font color=#66CC99 style=" font-weight:bold;">修改</font>。

##### 1.5 Java 中\=\=和 equals 的区别（必会)

== 的作用：

基本类型：比较的就是值是否相同

引用类型：比较的就是地址值是否相同

equals 的作用:引用类型：默认情况下，比较的是地址值。

特：String、Integer、Date 这些类库中 equals 被重写，比较的是内容而不是地址！
```java
public boolean equals(Object anObject) {  
    if (this == anObject) {  
        return true;  
    }    
    if (anObject instanceof String) {  
        String anotherString = (String)anObject;  
        int n = value.length;  
        if (n == anotherString.value.length) {  
            char v1[] = value;  
            char v2[] = anotherString.value;  
            int i = 0;  
            while (n-- != 0) {  
                if (v1[i] != v2[i])  
                    return false;  
	            i++;  
            }            
            return true;  
        
	    }    
    }    
    return false;  
}
```

未实现的equals
```java
	public boolean equals(Object obj) {
		return (this == obj);
	}	  
```



##### 1.5.2两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？
不对，两个对象的 hashCode()相同，equals()不一定 true。
```java
String s1 = "通话";  
String s2 = "重地";  
System.out.println(s1.hashCode());  
System.out.println(s2.hashCode());
//1179395
//1179395

```


##### 1.5.3什么是hashCode
字符串和对象有其hashCode
```java
	String s1 = "123";  
	String s2 = "111";  
	System.out.println(s1.hashCode());  
	System.out.println(s2.hashCode());
	//48690
	//48657
```
object类中
```java
public native int hashCode();
```

hashCode()存在的意义是什么？我们通过前面可以了解到hashCode()将一个字符串的值变为了一个整数，那么这样做的作用是什么呢？我们来看一段代码，如图。
![](img/Pasted%20image%2020220803004514.png)
我们平时经常用到map来存储对象，因为map是key，value形式的，它不像list形式的集合可以有顺序的从0开始往集合里放数据，而是随意的放，但是取值的话就很麻烦，因为它存放值的时候没有顺序，所以取值的时候根据key去里面一个一个对比，等找到key相等的值就取出，这样就会造成效率问题而<font color=#66CC99 style=" font-weight:bold;">hashCode的存在主要是用于查找的快捷性</font>。
当我们用到hashCode()可以看到我们将name计算为3373707，age计算为98511，这样的话我们存值的时候就根据计算后的数值进行对应位置的存储，同样当我们get取值的时候再次将key计算为hashCode()值，因为同一个字符串hashCode()值相等，这个时候我们就可以<font color=#66CC99 style=" font-weight:bold;">直接根据hashCode()值将对应位置的数据取出，就不需要对key一个一个进行对比了，这样大大提高了效率</font>，这就是为什么有hashCode()存在的原因了。


##### 1.6String、StringBuffer、StringBuilder 三者之间的区别（必会）

String 字符串常量

StringBuffer 字符串变量（线程安全）

StringBuilder 字符串变量（非线程安全）

String 中的 String 类中使用 final 关键字修饰字符数组来保存字符串，
<font color=#66CC99 style=" font-weight:bold;">private final char value[] ，String 对象是不可变的，也就可以理解为常量，线程安全。</font>

AbstractStringBuilder 是 StringBuilder 与 StringBuffer 的公共父类，定义了
一些字符串的基本操作，如 expandCapacity、append、insert、indexOf 等公共方法。
StringBuffer <font color=#66CC99 style=" font-weight:bold;">对方法加了同步锁</font>或者对调用的方法加了同步锁，所以是线程安全
的。

StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。

小结：

（1）如果要操作少量的数据用 String；

（2）多线程操作字符串缓冲区下操作大量数据用 StringBuffer；

（3）单线程操作字符串缓冲区下操作大量数据用 StringBuilder。

##### 1.6.2String str="i"与 String str=new String("i")一样吗？

不一样，因为内存的分配方式不一样。String str="i"的方式，java 虚拟机会将其分配到<font color=#66CC99 style=" font-weight:bold;">常量池</font>中；
而 String str=new String("i") 则会被分到<font color=#66CC99 style=" font-weight:bold;">堆内存</font>中。
就是因为String不可变
所以可以用常量池来存
因为多个引用指向的String不可变,每个引用都修改不了String的值


##### 1.7 接口和抽象类的区别是什么？（必会）

实现：抽象类的子类使用 extends 来继承；接口必须使用 implements 来实现接
口。
<font color=#66CC99 style=" font-weight:bold;">构造函数：抽象类可以有构造函数；接口不能有。</font>

main 方法：抽象类可以有 main 方法，并且我们能运行它；接口不能有 main 方
法。
实现数量：类可以<font color=#66CC99 style=" font-weight:bold;">实现很多个接口</font>；但是只能继承一个抽象类。

访问修饰符：<font color=#66CC99 style=" font-weight:bold;">接口中的方法默认使用 public 修饰</font>；抽象类中的方法可以是任意访
问修饰符

##### 1.7.2普通类和抽象类有哪些区别?

-   普通类不能包含<font color=#66CC99 style=" font-weight:bold;">抽象方法</font>，抽象类可以包含抽象方法。
    
-   抽象类不能直接实例化，普通类可以直接<font color=#66CC99 style=" font-weight:bold;">实例化</font>。


##### 1.7.3抽象类能使用 final 修饰吗？
  
不能，定义抽象类就是让其他类继承的，如果定义为 final 该类就不能被继承，这样彼此就会产生矛盾，所以 final 不能修饰抽象类

##### 1.8string常用的方法有哪些？（了解）

indexOf()：返回指定字符的索引。

charAt()：返回指定索引处的字符。

replace()：字符串替换。

trim()：去除字符串两端空白。

split()：分割字符串，返回一个分割后的字符串数组。

getBytes()：返回字符串的 byte 类型数组。

length()：返回字符串长度。

toLowerCase()：将字符串转成小写字母。

toUpperCase()：将字符串转成大写字符。

substring()：截取字符串。

equals()：字符串比较。



##### 1.12Java 的异常（必会）
![](img/Pasted%20image%2020220803011040.png)

Throwable 是所有 Java 程序中错误处理的父类，有两种子类：Error 和Exception。

Error：表示由 JVM 所侦测到的无法预期的错误，由于这是属于<font color=#66CC99 style=" font-weight:bold;"> JVM 层次的严重错误</font>，导致 JVM 无法继续执行，因此，这是不可捕捉到的，无法采取任何恢复的操作，顶多只能显示错误信息。

Exception：表示可恢复的例外，这是可捕捉到的。

1.运行时异常：都是 RuntimeException 类及其子类异常，如
NullPointerException(空指针异常)、IndexOutOfBoundsException(下标越界异常)等，
这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序
逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。运行时异常的特点是
<font color=#66CC99 style=" font-weight:bold;">Java 编译器不会检查它</font>，也就是说，当程序中可能出现这类异常，即使没有用 try-catch
语句捕获它，也没有用 throws 子句声明抛出它，也会编译通过。

2.非运行时异常（编译异常）：是 RuntimeException 以外的异常，类型上都属于 Exception
类及其子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。
如<font color=#66CC99 style=" font-weight:bold;"> IOException、 SQLException</font> 等以及用户自定义的 Exception 异常，一般情况下不自定
义检查异常。

常见的 RunTime 异常几种如下：

NullPointerException - 空指针引用异常

ClassCastException - 类型强制转换异常。

IllegalArgumentException - 传递非法参数异常。

ArithmeticException - 算术运算异常

ArrayStoreException - 向数组中存放与声明类型不兼容对象异常

IndexOutOfBoundsException - 下标越界异常

7NegativeArraySizeException - 创建一个大小为负数的数组错误异常

NumberFormatException - 数字格式异常

SecurityException - 安全异常

UnsupportedOperationException - 不支持的操作异常

##### 1.12.2java中的异常处理关键字是什么？
throw：有时我们明确要创建异常对象然后抛出它来停止程序的正常处理。throw关键字用于向运行时抛出异常来处理它。

throws：当我们在方法中抛出任何已检查的异常而不处理它时，我们需要在<font color=#66CC99 style=" font-weight:bold;">方法签名</font>中使用 throws关键字让调用者程序知道该方法可能抛出的异常。调用方法可以处理这些异常或使用throws关键字将其传播给它的调用方法。我们可以在throws子句中提供多个异常，也可以与main（）方法一起使用。

try-catch：我们在代码中使用try-catch块进行异常处理。try是块的开始，catch是在try块的末尾处理异常。我们可以使用try有多个catch块，try-catch块也可以嵌套。catch块需要一个应该是Exception类型的参数。

finally： finally块是可选的，只能用于try-catch块。由于<font color=#66CC99 style=" font-weight:bold;">异常会暂停执行过程</font>，因此我们可能会打开一些不会关闭的资源，因此我们可以使用finally块。<font color=#66CC99 style=" font-weight:bold;">finally块总是被执行,以防程序异常停摆</font>，无论是否发生异常。

##### 1.12.3final, finally, finalize的区别？
final用于声明属性，方法和类，分别表示属性不可交变，方法不可覆盖，类不可继承。    

finally是异常处理语句结构的一部分，表示<font color=#66CC99 style=" font-weight:bold;">总是执行</font>。

finalize是Object类的一个方法，在<font color=#66CC99 style=" font-weight:bold;">垃圾收集器执行的时候会调用被回收对象</font>的此方法，供垃圾收集时的其他资源回收，例如关闭文件等（在垃圾回收的时候会调用被回收对象的此方法）

##### 1.12.4try catch finally，try里有return，finally还执行么？
    执行，并且finally的执行早于try里面的return结论：

1、不管有没有出现异常，finally块中代码都会执行；

2、当try和catch中有return时，finally仍然会执行；

3、finally是在return后面的表达式<font color=#66CC99 style=" font-weight:bold;">运算后结果返回前执行</font>的

4、finally中最好不要包含return，否则程序会提前退出

##### 1.12.5什么情况finally不会执行
一共有三种

    1、不进入try块
       try语句没有被执行到，如在try语句之前就返回了，这样finally语句就不会执行；

    2、程序中止
        在try块中有System.exit(0);这样的语句，System.exit(0);是终止Java虚拟机JVM的，连JVM都停止了，所有都结束了，当然finally语句也不会被执行到。

    3、线程中止
    java线程分为两类，守护线程和非守护线程。当所有的非守护线程中止时，不论存不存在守护线程，虚拟机都会kill掉守护线程从而中止程序。 虚拟机中，执行main方法的线程就是一个非守护线程，垃圾回收则是另一个守护线程，main执行完，程序就中止了，而不管垃圾回收线程是否中止。 所以，如果守护线程中存在finally代码块，那么当所有的非守护线程中止时，守护线程被kill掉，其finally代码块是不会执行的。

##### 1.12.6thorw与thorws区别
位置不同

    throws 用在函数上，后面跟的是异常类，可以跟多个；而 throw 用在函数内，后面跟的是异常对象。

功能不同

    throws 用来声明异常，让调用者只知道该功能可能出现的问题，可以给出预先的处理方式；throw 抛出具体的问题对象，执行到 throw，功能就已经结束了，跳转到调用者，并将具体的问题对象抛给调用者。也就是说 throw 语句独立存在时，下面不要定义其他语句，因为执行不到。

```java
public class ThrowsDemo {
	public static void main(String[] args) {

		// 在调用方法处进行异常处理,亦可以抛出
		try {
			ThrowsDemo.testThrows();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 定义方法是使用throws抛出异常,谁调用谁处理
	public static void testThrows() throws ParseException {
		String string = "1999-09-15";

        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		System.out.println(simpleDateFormat.parse(string)); // 解析为日期

	}
}
```

