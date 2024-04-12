情况概述
在SpringBoot项目中使用“mvn package”进行打包操作时，打包生成的jar文件一般只有几十K左右，因为缺少依赖的jar包，所以无法直接运行。

为了能让SpringBoot运行，需要其生成的jar包和项目依赖的jar包等统一再次打包，然后才可以通过“java -jar"的方式运行。

将SpringBoot项目打包成一个可运行的jar文件，通常有两种方式：

在pom.xml中使用spring-boot-maven-plugin插件


在项目的pom.xml中配置spring-boot-maven-plugin插件，其中的关键goal是repackage。
```pom

    <build>
        <plugins>
           ...
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                	<!--项目的启动类位置-->
                    <mainClass>com.xxx.xxxxxxx.SpringDemoApplication</mainClass>
                   
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>


```
然后

```java
mvn package

```

生成jar包

部署
![[Pasted image 20220818012010.png]]