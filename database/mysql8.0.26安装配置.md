### [MySql](https://so.csdn.net/so/search?q=MySql&spm=1001.2101.3001.7020)-8.0.26 安装详细教程保姆级

*   [下载安装包](#_1)
*   [安装配置](#_11)
*   [配置环境变量](#_77)

下载安装包
=====

**下载安装包：**  
下载[网址](https://so.csdn.net/so/search?q=%E7%BD%91%E5%9D%80&spm=1001.2101.3001.7020)：  
[https](https://so.csdn.net/so/search?q=https&spm=1001.2101.3001.7020)://dev.mysql.com/downloads/  
选择这个  
![](https://img-blog.csdnimg.cn/81cc86510aa245848c28e03443f1a5ed.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)进入后选择  
![](https://img-blog.csdnimg.cn/ca8eb07d0b3d4ee3bc98892879519cd6.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)直接下载第一个  
![](https://img-blog.csdnimg.cn/86d9c69368714093894d3d58742fffc6.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)点击这里，开始下载  
![](https://img-blog.csdnimg.cn/338c5e3201db4f7f8678658075ae9a0d.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)

安装配置
====

**解压安装包**  
我这里解压到 d 盘  
打开  
![](https://img-blog.csdnimg.cn/770ef7859e5c43fd83ca557cda7e7dca.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)  
**编写 MySQL 配置文件**  
在解压目录下新建 my.[ini](https://so.csdn.net/so/search?q=ini&spm=1001.2101.3001.7020) 文件  
![](https://img-blog.csdnimg.cn/648a35f4427e48bd9ccb9c4a944befaa.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)  
将下面[文本](https://so.csdn.net/so/search?q=%E6%96%87%E6%9C%AC&spm=1001.2101.3001.7020)拷贝进 my,ini 文件中

```
[mysqld]
# 设置3306端口
port=3306
# 设置mysql的安装目录   ----------是你的文件路径-------------
basedir=D:\mysql-8.0.26-winx64\mysql-8.0.26-winx64
# 设置mysql数据库的数据的存放目录  ---------是你的文件路径data文件夹自行创建
#datadir=E:\mysql\mysql\data
# 允许最大连接数
max_connections=200
# 允许连接失败的次数。
max_connect_errors=10
# 服务端使用的字符集默认为utf8mb4
character-set-server=utf8mb4
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
# 默认使用“mysql_native_password”插件认证
#mysql_native_password
default_authentication_plugin=mysql_native_password
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8mb4
[client]
# 设置mysql客户端连接服务端时默认使用的端口
port=3306
default-character-set=utf8mb4
```

**初始化 MySQL 数据库**  
以管理员身份打开命令提示符  
切换到 bin 目录下  
![](https://img-blog.csdnimg.cn/869a4bdba57044c0b65be96e5e0e260e.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)  
在 MySQL 目录下的 bin 目录下执行命令：

```
mysqld --initialize --console
```

![](https://img-blog.csdnimg.cn/9df8256c4a1d4fc985e909eaf5d0c13b.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)我的随机密码是：2(eE8rwv#a(?  
**安装 mysql 服务并启动**

注意此处要用管理员权限打开

```
mysqld --install mysql
```

![](https://img-blog.csdnimg.cn/d04c844b7f00474aae444d3dde36b572.png)**启动 MySQL 服务**

```
net start mysql
```

![](https://img-blog.csdnimg.cn/a75360ed875940b8b68ece1dbc5c8d69.png)**连接 MySQL**

```
mysql -uroot -p
```

输入刚刚的随机密码  
![](https://img-blog.csdnimg.cn/f2402eeccffb47ce9c52753a1996e354.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)**修改密码**

```
ALTER USER 'root'@'localhost' IDENTIFIED BY '111111';
```

这里密码为 111111  
![](https://img-blog.csdnimg.cn/7eb2b873736c48e39c007118c957fe40.png)可以输入 quit 或者 exit 都可以退出  
登录就可以用 mysql -uroot -p

配置[环境变量](https://so.csdn.net/so/search?q=%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F&spm=1001.2101.3001.7020)
======================================================================================================

依次打开  
此电脑 -> 属性 -> 高级系统设置 -> 环境变量  
在系统变量中新建  
变量名：MYSQL_HOME  
变量值：MySQL 的目录  
![](https://img-blog.csdnimg.cn/2e6e9264fee14f9c9de653eedb79b809.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)![](https://img-blog.csdnimg.cn/413634695022499ab978450af1756eaf.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)然后在系统变量里面找到 path 变量  
添加 %MYSQL_HOME%\bin  
![](https://img-blog.csdnimg.cn/60a946d27ab84c69b95810936dde81c0.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)点击确定  
然后右键此电脑 -> 管理 -> 服务与应用程序 -> 服务  
找到 mysql 将启动类型改为手动  
![](https://img-blog.csdnimg.cn/0330658794d64d1f903b78dfad77373f.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2E4MDI5NzY=,size_16,color_FFFFFF,t_70)

一件三联吧

