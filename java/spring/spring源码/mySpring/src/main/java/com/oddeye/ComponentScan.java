package com.oddeye;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//target表示该注解只能加在类上面
@Target(ElementType.TYPE)
public @interface ComponentScan {
    //定义一个属性,来接收用户传过来的包扫描范围
    //String value() default "";//此处不用加default默认值,因为用户必须要输入包参数
    String value();
}

/*
SOURCE表示这个Annotation类型的信息只会保留在源码里，源码经过编译之后，Annotation的数据就会消失，
并不会保留在编译好的.class文件里；

CLASS表示这个Annotation类型的信息在源码保留，在.class文件也保留，
但不会把这些信息加载到虚拟机（JVM）中，如果不设置，系统默认值是CLASS；

RUNTIME表示在源码，编译后的.class都保存信息，
在执行的时候也会把这些信息加载到JVM中
*/