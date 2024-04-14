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

### 锁


##### 乐观锁
在 Java 中，乐观锁通常使用版本号机制或 CAS（Compare and Swap）操作实现。下面是一个简单的使用版本号机制实现乐观锁的示例：

```java
import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLockExample {
    private AtomicInteger version = new AtomicInteger(0);
    private int count = 0;

    public void increment() {
        // 获取当前版本号
        int oldVersion = version.get();
        
        // 模拟业务逻辑处理
        try {
            Thread.sleep(100); // 模拟耗时操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 检查版本号是否发生变化
        if (oldVersion == version.get()) {
            count++;
            // 更新版本号
            version.incrementAndGet();
        } else {
            System.out.println("Conflict detected. Retry...");
            increment(); // 重新尝试操作
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        OptimisticLockExample example = new OptimisticLockExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + example.getCount());
    }
}
```

在这个示例中，我们使用 `AtomicInteger` 类来模拟一个版本号，并在 `increment()` 方法中采用乐观锁的方式进行并发操作。在 `increment()` 方法中，首先获取当前的版本号，然后模拟业务逻辑处理，更新数据之前再次检查版本号是否发生变化，如果未发生变化则更新数据并更新版本号，否则进行重试操作。

通过使用版本号机制实现乐观锁，可以避免显式加锁的开销，提高并发性能和吞吐量。值得注意的是，在实际应用中，乐观锁的冲突检测和重试机制需要根据具体业务场景进行调整和优化。

>乐观锁通过在更新数据时先检查数据版本号（或者其他标识符）是否发生变化来保证一致性。当多个线程同时访问同一条数据时，他们会先读取数据的版本号，然后在写入时再次比对版本号。如果在写入时发现版本号已经被修改，说明有其他线程已经修改过该数据，那么当前线程的操作就会失败，需要进行相应的处理（比如重试或者放弃操作）。

>乐观锁在Java中通常基于版本号字段实现，当数据被修改时，版本号会随之增加。这样，只有拥有最新版本号的线程才能成功地完成更新操作，确保数据的一致性。如果多个线程同时尝试修改同一条数据，只有其中一个线程能够最终成功，其他线程则需要根据情况处理并重新尝试操作。

##### 悲观锁

在 Java 中，悲观锁通常通过 synchronized 关键字或 ReentrantLock 来实现。下面是一个使用 synchronized 实现悲观锁的简单示例：

```java
public class PessimisticLockExample {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        PessimisticLockExample example = new PessimisticLockExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + example.getCount());
    }
}
```

在这个示例中，`increment()` 方法和 `getCount()` 方法都被 synchronized 修饰，保证了在同一时间只有一个线程可以访问这两个方法，避免了并发访问导致的数据不一致性问题。

值得注意的是，synchronized 锁是基于对象的锁，所以在多线程操作时需要确保锁定的是同一个对象实例，否则无法达到预期的锁效果。

以上是一个简单的使用 synchronized 实现悲观锁的示例，更复杂的场景可能会使用 ReentrantLock 或其他并发工具类，根据具体需求选择合适的悲观锁机制。

### 多线程
![](img/Pasted%20image%2020240414153023.png)

##### 超时等待和等待状态区别
在Java多线程中，线程的状态包括timed_waiting状态和waiting状态，它们之间的区别如下：

1. **Timed_Waiting状态（计时等待状态）**：
   - 当线程调用sleep(long)、join(long)等方法时，会进入timed_waiting状态。
   - 在timed_waiting状态下，线程会等待一定的时间（超时时间），等待时间结束后线程会自动恢复到可运行状态。
   - 例如，当线程调用Thread.sleep(1000)时会进入timed_waiting状态，等待1秒后线程会重新变为可运行状态。

2. **Waiting状态（等待状态）**：
   - 当线程调用wait()、LockSupport.park()等方法时，会进入waiting状态。
   - 在waiting状态下，线程会无限期地等待，直到其他线程唤醒它。唤醒可以通过notify()、notifyAll()、interrupt()等方法来实现。
   - 例如，当线程调用object.wait()方法时会进入waiting状态，直到其他线程调用notify()或notifyAll()唤醒它。

总结：<font color=#99CCFF style=" font-weight:bold;">一个自己醒,另一个是多线程协同,一个唤醒另一个</font>

##### 两状态使用场景
1. **Timed_Waiting状态适用场景**：
   - **定时任务**：当需要在线程执行特定操作后等待一段时间再继续执行时，可以使用Timed_Waiting状态。比如定时任务、定时轮询等。
   - **资源预留**：如果线程需要在一段时间内暂时不占用CPU资源，可以通过调用Thread.sleep()、Object.wait(long)等方法进入Timed_Waiting状态。
   - **超时等待**：在需要防止某些操作无限期等待导致程序无法继续的情况下，可以设置一个超时时间，超时后线程会自动恢复执行。

2. **Waiting状态适用场景**：
   - **线程间协作**：当线程需要等待其他线程发出信号或满足某个条件后再继续执行时，可以使用Waiting状态。通常配合关键字synchronized使用。
   - **事件驱动**：在事件驱动的编程模型中，线程可能需要等待某个事件触发后才能继续执行，这时可以使用Waiting状态。
   - **生产者消费者模型**：在处理生产者消费者模式时，消费者线程可能需要等待生产者线程生产数据后进行处理，这时可以通过使用Waiting状态来实现。

总结：<font color=#99CCFF style=" font-weight:bold;">一个是单纯的在等,另一个是多线程协调,完全不一样</font>


##### notify和wait
<font color=#99CCFF style=" font-weight:bold;">notify唤醒其他线程争夺锁</font>
<font color=#99CCFF style=" font-weight:bold;">其他线程进入synchronized中后就会获取该对象的锁</font>
<font color=#99CCFF style=" font-weight:bold;">在某些条件下当前线程会使用wait方法释放进入synchronized获取的锁</font>

主要注意点包括：

1. `notify()`方法必须在同步块或同步方法内部调用，否则会抛异常。
2. 被唤醒的线程并不能立即执行，而需要等待调用`notify()`的线程释放锁后才能继续执行。
3. 如果有多个线程在等待同一个对象锁，调用`notify()`只会唤醒其中一个线程，具体唤醒哪一个线程由线程调度器决定。
4. 如果想唤醒所有等待的线程，可以使用`notifyAll()`方法，它会唤醒所有等待的线程。

在并发编程中，`notify()`方法通常与`wait()`方法配合使用，实现线程之间的协调与通信。其中`notify()`方法可以用来通知消费者线程可以消费数据了。


下面展示了如何使用`wait`和`notify`方法实现生产者-消费者模型：

```java
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity = 5;

    public void produce() throws InterruptedException {
        synchronized (this) {
            while (queue.size() == capacity) {
                wait();
            }
            int value = (int) (Math.random() * 100);
            System.out.println("Producing: " + value);
            queue.add(value);
            notify();
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }
            int value = queue.poll();
            System.out.println("Consuming: " + value);
            notify();
        }
    }

    public static void main(String[] args) {
        ProducerConsumerExample pc = new ProducerConsumerExample();

        Thread producerThread = new Thread(() -> {
            try {
                while (true) {
                    pc.produce();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    pc.consume();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
```

在这个例子中，`ProducerConsumerExample`类中有一个队列`queue`作为共享的缓冲区，`produce`方法负责向队列中添加数据，`consume`方法负责从队列中取出数据。生产者线程不断生产数据，消费者线程不断消费数据，当队列满时消费者线程等待，当队列为空时生产者线程等待，通过`wait`和`notify`方法实现线程之间的协调。

>两个生产者,一个消费者
>对于wait和notify都属于this这个实例对象的方法
>所以三个线程都围绕着一个实例对象来上锁和释放锁
>当队列满时
>两个生产者都会wait然后释放锁,唤醒其他线程争抢锁, 这时消费者就能获得锁, 然后队列减少并唤醒其他线程争夺锁

##### sleep方法
只是让当前线程释放cpu,换别的线程获取cpu资源,
但<font color=#99CCFF style=" font-weight:bold;">没有释放锁给其他线程争抢,所以所有线程都停止了</font>
与wait方法相比,sleep不需要考虑多个线程争夺一个资源的问题

##### join方法
等效为: <font color=#99CCFF style=" font-weight:bold;">当前线程sleep到某线程结束后继续执行</font>
```java
某线程b.join()   ==> 当前线程a.sleep_until(线程b结束完成)
```
当调用某个线程的join方法时，当前线程会等待该线程执行完成后再继续执行。下面是一个具体的生产场景示例：

假设在一个多线程爬虫应用中，需要爬取多个网页内容，并在所有内容都爬取完毕后进行数据处理和分析。可以通过使用join方法来实现：

```java
public class WebCrawler extends Thread {
    private String url;
    private String content;

    public WebCrawler(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        // 模拟爬取网页内容的操作
        System.out.println("Crawling content from: " + url);
        // 省略实际的爬取逻辑
        content = "<html>...</html>";
    }

    public String getContent() {
        return content;
    }

    public static void main(String[] args) throws InterruptedException {
        WebCrawler crawler1 = new WebCrawler("http://example.com/page1");
        WebCrawler crawler2 = new WebCrawler("http://example.com/page2");

        // 启动两个爬虫线程
        crawler1.start();
        crawler2.start();

        // 等待两个线程执行完成
        crawler1.join();
        crawler2.join();

        // 所有内容都已经爬取完后进行数据处理和分析
        String content1 = crawler1.getContent();
        String content2 = crawler2.getContent();
        
        // 处理和分析数据
        System.out.println("Content from page1: " + content1);
        System.out.println("Content from page2: " + content2);
    }
}
```

在上述示例中，我们创建了两个WebCrawler线程分别用于爬取两个网页的内容，然后通过join方法等待两个线程执行完成后再进行数据处理和分析。这样可以保证在所有内容都爬取完毕后再进行后续操作，确保数据处理的准确性和完整性。

##### wait和join区别
wait()方法和join()方法虽然都能控制线程的执行顺序，但是`wait()`主要用于线程间的通信和协作，而`join()`主要用于等待其他线程的执行结果或确保线程执行顺序。

wait释放锁,所以当前线程没锁了,虽然已经进入了synchronized中,但是在wait处停住了,处于<font color=#F09B59 style=" font-weight:bold;">等待状态</font>,直至另一个程序释放锁唤醒其他线程争夺锁
join完全不管争夺资源的事,主线程处于<font color=#F09B59 style=" font-weight:bold;">超时等待状态</font>等另一个线程执行完后,自然就继续执行了
```java
public void produce() throws InterruptedException {
        synchronized (this) {
            while (queue.size() == capacity) {
                wait();
            }
            int value = (int) (Math.random() * 100);
            System.out.println("Producing: " + value);
            queue.add(value);
            notify();
        }
    }
```

### 线程池

##### 线程池调优
在调优线程池时，通常需要调节以下几个参数来提高性能和效率：

1. 核心线程数（corePoolSize）：
   最小线程数,
   即使线程是空闲状态，线程池也会保持这些核心线程不会被回收，以便快速响应任务的到来

2. 最大线程数（maximumPoolSize）：
   
3. 阻塞队列：
   - 不同类型的阻塞队列会影响线程池的任务调度策略。比如先进先出,时间片轮转,后进先出
   - 设置阻塞队列容量

4. 拒绝策略（RejectedExecutionHandler）：
   - 当线程池和任务队列都达到最大容量时，会触发拒绝策略来处理无法执行的任务。
   - 根据业务需求选择合适的拒绝策略，如抛出异常、丢弃任务、调用者运行等。

5. 空闲线程存活时间：
   超过会回收

### 集合
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

hashCode()存在的意义是什么？我们通过前面可以了解到hashCode()将一个字符串的值变为了一个整数，那么这样做的作用是什么呢？

1. **对象相等性判断依据**：在Java中，根据对象的哈希码可以快速判断两个对象是否相等。当两个对象的哈希码不相等时，可以直接判定这两个对象不相等；当两个对象的哈希码相等时，还需要通过`equals()`方法进行深度比较来确保对象的相等性。
    
2. <font color=#99CCFF style=" font-weight:bold;">hash表中快速定位对象</font>


我们平时经常用到HashMap,HashSet来存储对象，因为map是key，value形式的，它不像list形式的集合可以有顺序的从0开始往集合里放数据，而是随意的放，但是取值的话就很麻烦，因为它存放值的时候没有顺序，所以取值的时候根据key去里面一个一个对比，等找到key相等的值就取出，这样就会造成效率问题而<font color=#66CC99 style=" font-weight:bold;">hashCode的存在主要是用于查找的快捷性</font>。
当我们用到hashCode()可以看到我们将name计算为3373707，age计算为98511，这样的话我们存值的时候就根据计算后的数值进行对应位置的存储，同样当我们get取值的时候再次将key计算为hashCode()值，因为同一个字符串hashCode()值相等，这个时候我们就可以<font color=#66CC99 style=" font-weight:bold;">直接根据hashCode()值将对应位置的数据取出，就不需要对key一个一个进行对比了，这样大大提高了效率</font>，这就是为什么有hashCode()存在的原因了。
### 栈
