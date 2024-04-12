### static修饰变量
一个成员变量被static修饰后就是全局变量
只要包含这个全局变量的类被加载
该变量就可以分配到内存空间
因此static修饰的成员方法属于类,只有一个副本
<font color=#F09B59 style=" font-weight:bold;">所有由该类为模板创建的对象共用这个副本</font>

而普通实例变量是在对象被创建后才会被分配内存空间

### 变量分类
##### 变量分类
* 局部变量
* 成员变量
$$成员变量
\begin{cases}
实例变量\\
静态变量
\end{cases}$$
实例变量属于对象的
静态变量属于类的
![](img/Pasted%20image%2020221020162637.png)
##### 静态变量的特点
采用静态的存储方式
而是其存在一个固定的地方,就一个,不会因为多个对象而创建多个
只有一个副本

##### 常量和静态变量的区别
常量是加final,意味不可变
静态变量代表只有一个,但不代表不可变,不可被修改
##### c语言中静态局部变量的概念?
生存周期上来说
是整个程序执行期间一直存在,但因为是局部变量
因此不能直接访问,需要调用其函数
不会像普通的局部变量那样,函数一执行完毕就会被释放
因为不会被释放,一直存在,因此可以用来记录某一函数的活动数据

##### java中为什么没有静态局部变量?
![](img/Pasted%20image%2020221020160248.png)
本身就是java的语法规定
static设计之初衷就是为类而设计的
因此其用以修饰属于类的成员变量和成员方法
java里<font color=#66CC99 style=" font-weight:bold;">方法一结束就要销毁所有局部变量,释放内存空间</font>
你拿static修饰了局部变量,不方便回收
因为方法里的局部静态变量完全可以用类的静态变量代替
比如说记录<font color=#66CC99 style=" font-weight:bold;">所有该对象的该方法被调用多少次,你可以用成员变量里的静态变量来实现计数</font>
比如说你要<font color=#66CC99 style=" font-weight:bold;">记录自该对象被创建后该方法被调用多少次,你可以直接用实例变量就可以计数</font>

从内存的空间排布上思考,为什么方法里放静态变量不好?
因为你方法执行完毕释放空间后
你那一块区域里的静态变量没有被释放,造成方法被释放后那一块空闲内存不连续
因此
对象和类分开放在不同的内存空间

##### 为什么静态方法不能访问实例变量
![](img/Pasted%20image%2020221020162653.png)
因为<font color=#F09B59 style=" font-weight:bold;">静态方法和实例变量的生命周期不同</font>
被static修饰的成员变量/成员方法,在类创建时就会被分配空间
而要访问的实例变量,只有在对象被创建后才会被分配空间
类肯定是优先于对象而存在
<font color=#FFCCCC style=" font-weight:bold;">对象还没被创建,你静态成员方法怎么访问?</font>
所以进一步延伸
在static修饰的成员方法中
<font color=#66CC99 style=" font-weight:bold;">this和super也一样不能用</font>
因为this和super调用的也是实例对象里的实例属性和方法


##### static代码块
不需要被任何方法调用,在jvm加载时就自动执行,且只执行一次
类似于<font color=#66CC99 style=" font-weight:bold;">脚本</font>
static修饰的代码块和static修饰的成员方法一样不能访问实例变量
因此<font color=#66CC99 style=" font-weight:bold;">其主要作用就是初始化静态变量</font>

##### static内部类
<font color=#99CCFF style=" font-weight:bold;">暂时不知道内部类有啥用</font>

##### static唯一副本特性
因为static修饰的成员变量在内存中只有一个副本
因此可以利用该特性在单例模式中
单例模式的主要诉求就是一个类只能有一个对象
当然这个对象会变,没必要加final
<font color=#FFCCCC style=" font-weight:bold;">那么怎么让一个类只有一个对象?</font>
把存这个类的对象变量加个static就行了
```java
class Singleton{
    private static Singleton instance=null;
    
    private Singleton(){}
    
    public static Singleton getInstance(){
    
        if (instance=null){
        
            instance=new Singleton();
        }
        return instance;
   }
}
```
那么为什么上面这个成员方法也加static呢?
<font color=#FFCCCC style=" font-weight:bold;">你要不加static,没对象,你怎么调用该方法?</font>
对象你无权new,你只能通过类.方法的方式调用方法
因此getInstance必须加static