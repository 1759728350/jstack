
### SpringBoot/Maven项目中的 ClassPath路径指的是哪些路径

在使用SpringBoot时，我们把静态资源放在指定的目录，就能直接访问到。也可以在yml中修改静态资源的位置 `spring.web.resources.static-locations=classpath:/images/`
![[Pasted image 20220818005832.png]]

但是，classpath到底是指哪些路径呢？

1、首先，对于SpringBoot/Maven项目来说，classpath指的是<font color=#66CC99 style=" font-weight:bold;">src.main.java和src.main.resources路径以及第三方jar包的根路径</font>，存放在这两个路径下的文件，都可以通过classpath作为相对路径来引用；

`src.main.java`和`src.main.resources`路径下的内容，在执行了Maven的Compile后，都会被放到`target.classes`目录下，包括.class文件，静态资源文件（图片，CSS，jQuery文件等）；
第三方jar则不会被放入到里面,因为有了pom.xml

![[Pasted image 20220818005527.png]]
mybatis的配置文件虽然在resource文件夹中但在<font color=#66CC99 style=" font-weight:bold;">compile后</font>会根据文件名生成到了对应的com文件夹里
![[Pasted image 20220818005641.png]]

### classpath经常在xml或者yml,properties/java中读取一些配置文件
把配置文件如：mybatis.xml、spring-web.xml、applicationContext.xml等放到src目录（就是存放代码.java文件的目录），然后使用“classpath：xxx.xml”来读取

```xml
<param-value>classpath:context/conf/controller.xml</param-value>


```

### classpath 和 classpath* 区别：  
classpath：只会到你的class路径中查找找文件;  
classpath*：不仅包含class路径，还包括jar文件中(class路径)进行查找.