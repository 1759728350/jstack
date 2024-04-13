##### volatile
假设有一个简单的计数器类Counter，代码如下：

```java
public class Counter {
    private volatile int count;

    public Counter() {
        this.count = 0;
    }

    public void increase() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

现在我们来说明一下`volatile`关键字的作用。

假设有两个线程A和B同时对Counter对象进行操作：

线程A执行increase()方法，将count增加1；同时，这个修改操作并不是立即写入主内存，而是先修改线程A的工作内存里的count值，然后再刷新到主内存。

在这个过程中，如果线程B也执行getCount()方法去获取count值，由于count没有被volatile修饰，线程B可能会看到一个旧的count值，而不是线程A最新增加后的值。

但是，如果我们把count字段声明为volatile，则每次修改count值后，都会立即将最新的值写入主内存，这样其他线程就可以立即看到最新的值，从而避免了数据不一致的问题。

因此，通过给count字段添加volatile关键字，可以确保多线程环境下对count值的修改和读取操作都是可见的，确保了操作前后的有序性, 防止读到旧值。

>`volatile`修饰变量确实可以保证可见性和有序性，但它并不相当于给这个变量创建一个锁。实际上，<font color=#99CCFF style=" font-weight:bold;">`volatile`关键字提供的是一种轻量级的同步机制，而不是重量级的锁</font>。


##### 给变量创建重量级锁
要给一个变量上一个重量级的锁，通常可以使用`synchronized`关键字或`ReentrantLock`类来实现。这两种方式都能提供重量级的同步机制，确保在同一时刻只有一个线程可以访问共享资源，从而保证线程安全。

1. 使用`synchronized`关键字：

`synchronized`关键字可以直接作用于代码块或方法，也可以作用于对象实例或类。当`synchronized`修饰一个方法或代码块时，它会获取对象实例或类的锁，确保同一时刻只有一个线程可以执行被`Synchronized`修饰的方法或代码块。

示例代码如下：

```java
public class SyncExample {
    private Object lock = new Object();
    private int count = 0;

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}
```

在上面的示例中，通过`synchronized`关键字对`increment()`和`getCount()`方法进行了同步，使用`lock`对象作为锁对象，确保在同一时刻只有一个线程可以对`count`变量进行操作。

2. 使用`ReentrantLock`类：

`ReentrantLock`是Java提供的显式锁，它提供了更灵活的锁定机制，可以手动控制锁的获取和释放。使用`ReentrantLock`可以实现更复杂的同步需求，例如可定时、可中断的锁等待、公平锁等。

示例代码如下：

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private Lock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
```

在上面的示例中，通过`ReentrantLock`对`increment()`和`getCount()`方法进行了同步，确保在同一时刻只有一个线程可以对`count`变量进行操作，并通过`lock()`和`unlock()`方法手动控制锁的获取和释放。