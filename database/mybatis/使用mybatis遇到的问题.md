### 主要功能
为插入语句自动生成id

### 文档
[文档地址](https://baomidou.com/pages/223848/#tableid)

### mapper.xml没有被打包

<font color=#66CC99 style=" font-weight:bold;">BingingExecption</font>
```
<build>  
    <plugins>  
        <plugin>  
            <groupId>org.springframework.boot</groupId>  
            <artifactId>spring-boot-maven-plugin</artifactId>  
        </plugin>  
    </plugins>  
  
    <resources>  
        <resource>  
            <directory>src/main/java</directory>  
            <includes>  
                <include>**/*.properties</include>  
                <include>**/*.xml</include>  
            </includes>  
            <filtering>false</filtering>  
        </resource>  
        <resource>  
            <directory>src/main/resources</directory>  
            <includes>  
                <include>**/*.properties</include>  
                <include>**/*.xml</include>  
            </includes>  
            <filtering>false</filtering>  
        </resource>  
    </resources>  
  
  
</build>
```
上面的plugin是maven打包插件,用于将项目打包为可直接运行的jar
[../../project_blog/springboot项目打包](../../project_blog/springboot项目打包)

下面这个resource是扫描文件时将xml文件也要打包进去
最后在target目录下确保xml和mapper接口在一起
![](img/Pasted%20image%2020220910022124.png)


### QueryWrapper的使用
```java
QueryWrapper<Commit> wrapper = new QueryWrapper<>();  
wrapper.eq("co_cid",carId);
List<Commit> commitList = commitMapper.selectList(wrapper);
```
像eq这样的需要字段来进行过滤时,我们会产生疑问
<font color=#99CCFF style=" font-weight:bold;">究极是传bean的对应字段还是传数据库的对应字段?</font>
答案是传<font color=#66CC99 style=" font-weight:bold;">数据库的字段</font>

mybatisplus本身就是为了简化而自动生成sql语句
所以其核心就是通过数据库中的字段才能去生成sql
因此我们把bean中的字段"化妆"成数据库字段的样子
借此更轻松的达成与表的映射
映射完后,只需要对bean操作就是对表操作
因此核心还是围绕着表




### mybatis的parameterType传参
https://blog.csdn.net/xiaojin21cen/article/details/81069811?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166274780616782428610199%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=166274780616782428610199&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-3-81069811-null-null.142^v47^new_blog_pos_by_title,201^v3^control_1&utm_term=parameterType&spm=1018.2226.3001.4187
![](img/Pasted%20image%2020220910065333.png)