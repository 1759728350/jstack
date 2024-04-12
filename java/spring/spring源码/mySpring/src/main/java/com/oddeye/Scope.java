package com.oddeye;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//bean分为单例和原形
//如果不加scope注解说明这个class创建bean是单例的(默认)
//何为原形bean?
//getBean获取到的对象每次都不一样
//何为单例bean?
//getBean获取到的对象是一样的
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {
    //那么如何实现单例bean呢?
    //map<beanName,bean对象>
    String value();
}
