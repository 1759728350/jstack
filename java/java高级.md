### 接口
##### 接口的设计意义
1. 实现多继承：Java中不支持类的多继承，但是可以通过接口实现多继承的效果。一个类可以实现多个接口，从而获得接口中定义的各种方法。

2. 实现规范和约束：接口定义了一组方法的规范，通过实现接口可以确保类具有特定的行为。这样可以提高代码的可读性和可维护性。

3. 解耦合：接口可以将类之间的耦合度降低到最低限度，类只需实现接口定义的方法，而无需关心具体的实现细节。这样可以提高代码的灵活性和扩展性。

4. 实现回调机制：接口可以用于实现回调（Callback）机制，即某个类在特定事件发生时调用另一个类的方法。这种机制在事件处理、UI编程等方面有广泛的应用。

5. 接口与抽象类的区别：接口定义了一组方法的规范，而抽象类可以包含成员变量和方法的实现。接口强调的是“做什么”，而抽象类强调的是“是什么”。

总之，Java设计接口的主要目的是为了实现多继承、规范约束、解耦合、回调机制等功能，使得程序设计更加灵活、可扩展和易维护。接口是Java面向对象编程的重要特性之一，被广泛应用在实际项目中。

### 关键字
#### static
##### 设计意义
在Java中，`static`关键字的主要作用是用来修饰变量、方法和代码块，其存在的意义包括以下几个方面：

1. **共享数据**：使用`static`修饰的变量属于类级别而不是实例级别，即该变量会被所有实例共享。这样可以在不同对象之间共享数据，提高了数据的共享性和可访问性。

2. **单例模式**：通过将构造方法私有化并在类中使用`static`修饰一个实例变量，并提供一个静态方法获取该实例，可以实现单例模式，确保系统中只有一个实例存在。

3. **内存管理**：`static`修饰的变量存储在静态存储区域，在程序启动时即被分配内存，在程序结束时才会被释放。这有助于在整个程序的生命周期内保持数据的运行状态。

4. **工具方法**：使用`static`修饰的方法可以直接通过类名调用，不需要实例化对象，适用于一些工具方法或工具类。

5. **静态块**：使用`static`修饰的代码块在类加载时执行，可以用于初始化静态变量或进行一些静态内容的初始化操作。

总的来说，Java设计`static`关键字主要是为了实现数据共享、单例模式、确保变量一直在内存中、工具方法等功能。合理地使用`static`关键字可以提高代码的复用性、可维护性和效率，使得程序在不同场景下具有更强大的灵活性。
##### 类级变量和方法
<font color=#99CCFF style=" font-weight:bold;">static让变量成为类级别的变量 </font>
<font color=#FFCCCC style=" font-weight:bold;">类级变量特点: </font>
* 程序一开始就会被分配内存
* 变量一直在内存中不会释放直至程序结束
* 只有一份
* 每个程序可以直接赋值使用,可以改
<font color=#FFCCCC style=" font-weight:bold;">类级方法特点:</font>
* 在不将类new出来就可以直接调用方法

#### final
设计的初衷是为了 =>避免了其他开发人员对已经确定的部分进行修改,导致程序出错
<font color=#99CCFF style=" font-weight:bold;">类不能被继承、方法不能被重写、一次性变量(变量可赋值,  但被赋值后不能被修改)</font>

##### final赋值
final只能在声明时已经被手动赋值
或
在构造器里被赋值,
其他地方都不能被赋值

```java
/*
一般情况下
实例变量如果还没有赋值的话，系统会赋默认值

final 修饰实例变量：
系统不负责赋默认值，要求程序员必须手动赋值，只能赋一次，
这个手动赋值，在变量后面赋值可以，在构造方法中赋值也可以
*/
public class FinalTest02(){
    public static void main(String[]args){
        
    }
}
class User{
    //实例变量
    //错误： 变量age未在默认构造器中初始化
    //final int age;
    final int age=10;
    
    //在构造方法中赋值 weight只赋一次值
    final double weight;
    //构造方法
    public User(){   
        this.weight=80;
        //系统赋默认值在这个时候,final修饰后，系统不会赋值
        //this.weight=0;
    }
}
```
##### 懒汉单例综合final和static
当在多线程环境中使用`final`修饰变量时，一个常见的实际生产中的代码例子是用来创建线程安全的单例模式。下面是一个简单的示例代码：

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

在上面的示例中，`instance`变量被使用`final`修饰，保证了在多线程环境下对单例对象的访问是安全的。由于`instance`是静态变量，因此只会在类加载的时候进行初始化，保证了对象的唯一性。

在这个示例中，final和static的作用：

1. 可见性：一旦`instance`变量被初始化后，在所有其他线程中都能立即看到这个变量的最新值。
2. 不可修改性：`instance`变量被声明为`final`，表示<font color=#99CCFF style=" font-weight:bold;">这个引用指向的对象不会改变,无法被修改，从而避免了多线程并发修改引起的问题</font>。
3. 实例变量赋值：因为<font color=#99CCFF style=" font-weight:bold;">static关键字修饰变量所以`instance`变量在类加载的时候被赋值</font>，保证了在对象构造完成之前被正确初始化，确保了对象的稳定性。
4. static保证了这个变量只有一份,保证唯一性
5. 后面的方法上加上static是因为这个类无法new创建,因此让其他调用者可以全局调用,不用new对象

这种方式实现的单例模式保证了在多线程环境下对单例对象的安全访问，同时利用`final`关键字提供了额外的保障。这样可以避免多线程并发访问时出现的问题，确保单例对象的唯一性和一致性。

##### 饿汉单例分析final
懒汉式单例模式在第一次调用`getInstance()`方法时才创建实例，如果不考虑线程安全性，可能会导致多个线程同时进入创建实例的代码块，从而创建多个实例，破坏了单例模式的初衷。为了解决这个问题，引入了双重检查锁定机制。

以下是双重检查锁定的懒汉式单例示例代码：

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) { // 第一次检查，避免不必要的同步检查,可有可无
							    //如果已经new完,后续的线程不会重复获取锁
			//会有多个线程同时判断==null然后在这里排队,所以第二个==null是必要的
            synchronized (Singleton.class) {
                if (instance == null) { 
	                //synchronized只负责排队,但又不是不让第二个进
	                //所以第二个来了,第三个来了,就没必要再创建了
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

在上面的代码中，使用`volatile`关键字修饰`instance`字段可以保证多个线程能够正确处理实例的可见性。防止编译器进行读写重排优化操作
双重检查锁定:
* 第一次检查，避免不必要的同步检查,可有可无，果已经new完,后续的线程不会重复获取锁
* 第二次检查，第一个创建完毕后其他线程就没必要再创建了, 还能防止多个线程已经`==`null判断完毕在竞争锁进行排队

不加变量上final是因为final修饰的变量只能在声明和构造器内就得被赋值,不能达到延迟的效果

### 异常

##### Java异常族谱
![](img/Pasted%20image%2020220803011040.png)

Throwable 是所有 Java 程序中错误处理的父类，有两种子类：Error 和Exception。

Error：表示由 JVM 所侦测到的无法预期的错误，由于这是属于<font color=#66CC99 style=" font-weight:bold;"> JVM 层次的严重错误</font>，导致 JVM 无法继续执行，因此，这是不可捕捉到的，无法采取任何恢复的操作，顶多只能显示错误信息。

Exception：表示可恢复的例外，这是可捕捉到的。


##### 1.运行时异常：
都是 RuntimeException 类及其子类异常，如
NullPointerException(空指针异常)、IndexOutOfBoundsException(下标越界异常)等，
这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序
逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。运行时异常的特点是
<font color=#66CC99 style=" font-weight:bold;">Java 编译器不会检查它</font>，也就是说，当程序中可能出现这类异常，即使没有用 try-catch
语句捕获它，也没有用 throws 子句声明抛出它，也会编译通过。

##### 2.非运行时异常（编译异常）
是 RuntimeException 以外的异常，类型上都属于 Exception
类及其子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过。
如<font color=#66CC99 style=" font-weight:bold;"> IOException、 SQLException</font> 等以及用户自定义的 Exception 异常，一般情况下不自定
义检查异常。

常见的 RunTime 异常几种如下：

NullPointerException - 空指针引用异常

ClassCastException - 类型强制转换异常。

ArithmeticException - 算术运算异常

IndexOutOfBoundsException - 下标越界异常

NumberFormatException - 数字格式异常


### 栈
要确保前一个线程彻底执行完毕并释放锁后，其他线程才能竞争到锁，可以使用`wait()`和`notify()`或者`join()`方法来实现线程之间的协作。这样可以确保前一个线程在执行完毕后再释放锁，从而避免其他线程过早地竞争到锁。

下面是一个简单的示例，演示如何使用`wait()`和`notify()`方法实现线程间的协作：

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        synchronized(Singleton.class) {
            if (instance == null) {
                instance = new Singleton();
            } else {
                try {
                    Singleton.class.wait(); // 当前线程等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Singleton.class.notify(); // 唤醒其他等待线程
            return instance;
        }
    }
}
```

在上面的示例中，如果 `instance` 已经被创建，当前线程会调用`wait()`方法等待，直到其他线程调用`notify()`方法唤醒它。这样就可以确保前一个线程执行完毕后再释放锁给其他线程竞争。

另一种方法是使用`join()`方法，让其他线程在前一个线程执行完毕之后再继续执行。例如：

```java
Thread t1 = new Thread(new Runnable() {
    public void run() {
        // 创建对象的代码
    }
});
t1.start();
try {
    t1.join(); // 等待t1线程执行完毕
} catch (InterruptedException e) {
    e.printStackTrace();
}
// 继续执行其他代码
```

通过以上方式，可以确保前一个线程彻底执行完毕之后释放锁，其他线程再来竞争获取锁。