java.io.file类

其构造方法可以将一个路径转化为一个文件或者目录,所以file类是文件或目录的抽象表现形式
文件夹和文件视作同一file
通过file类我们可以对操作系统中的文件进行操作

* 创建文件 ^18bcc5
* 删除文件
* 获取文件/遍历文件/文件大小 ^6cb169
* 判断文件是否存在

##### 文件构造

```java
//构造对象本身不会创建文件
File file = new File("相对路径/绝对路径")
//parent既可以是一个父路径,也可以是
//一个已创建的file目录对象
File file = new File(parent,child)
```

##### 文件名及路径分割符
* getName获取文件最后的名字
* getPath获取创建文件时file构造函数的参数
* getAbsolutePath获取文件的绝对路径
* File.separator跨系统文件分隔符
* length返回文件大小(路径错误或者是文件夹则返回0,文件夹没有大小)


```java
//分隔符取决于你的操作系统,这也是java跨平台的特点
String separator = File.separator;  
File file = new File("D:"+separator+"未返校未做04.17全员核酸汇总名单(2).xlsx");  
System.out.println(file.getName());  
//未返校未做04.17全员核酸汇总名单(2).xlsx
System.out.println(file.getPath()); 
//D:\未返校未做04.17全员核酸汇总名单(2).xlsx

//删除文件
System.out.println(file.delete());

```

windows中分隔符\\ linux分隔符为/     
java中由于有转义字符\\的存在所以在描述windows路径是会自动变为\\\

##### 删除文件

```java
public boolean createNewFile():当且仅当具有该名称的文件尚不存在
时，创建一个新的空文件。
public boolean delete()∶删除由此File表示的文件或目录。
public boolean mkdir():创建由此File表示的目录。
public boolean mkdirs()︰创建由此File表示的目录，包括任何必需但不存在的父目录。
```

##### 判断文件

```java
public boolean exists():此File表示的文件或目录是否实际存在。
public boolean isDirectory():此File表示的是否为目录。
public boolean isile():此File表示的是否为文件。
```
isDirectory/isFile该file对象是否为目录/文件
一个file对象要么是文件要么是目录,除非路径错误

##### 创建文件

```java
public boolean createNewFile()
当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。
该方法不能用于创建文件夹

public boolean delete()删除由此File表示的文件或目录。
文件夹里有文件则删不掉且返回false

public boolean mkdir)创建由此File表示的目录。
public boolean mkdirs()︰创建由此File表示的目录，包括任何必需但不存在的父目录。

```
```java
File file = new File("new.txt");  
System.out.println(file.createNewFile());
```
![[img/Pasted image 20220816184715.png]]

##### 获取文件

```java
public String[] list()
返回一个String数组，表示该File目录中的所有子文件或目录。
public File[] listiiles()
返回一个File数组，表示该File目录中的所有的子文件或目录。

```

##### 实践-递归文件夹
```java
import java.io.File;  
import java.io.InputStream;  
  
public class jj {  
    public static void main(String[] args) throws Exception{  
  
        //将文件读取  
        File file = new File("jjk");  
        int num = 1;  
        read(file,num);  
  
    }  
    public static void read(File file,int num){  
  
        //循环遍历  
        for (File subFile:file.listFiles()) {  
            //如果是文件夹递归,输出文件夹名  
            if (subFile.isDirectory()){  
                System.out.println(offset(num)+num+"级文件夹:"+subFile.getName());  
                num++;  
                read(subFile,num);  
                num--;  
            }else {  
                //如果不是输出文件名  
                System.out.println(offset(num)+num+"级文件:"+subFile.getName());  
  
            }        
        }    
    }    
            
            
    public static String offset(int num){  
        StringBuilder s = new StringBuilder();  
        for (int i = 0;i < num;i++){  
            s.append("--");  
        }        return s.toString();  
    }  
  
}


```
输出
```java
--1级文件夹:aa
----2级文件:sss - 副本.txt
----2级文件:sss.txt
--1级文件夹:bb
----2级文件夹:jkjbb
------3级文件:新建 文本文档.txt
----2级文件:sss.txt
--1级文件夹:cc

```
