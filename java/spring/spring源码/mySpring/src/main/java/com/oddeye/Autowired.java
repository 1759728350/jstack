package com.oddeye;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 **description: 实现属性自动注入
 **param&method:
 **caller: 所有需要自动注入的属性
 **problems:
 */
@Retention(RetentionPolicy.RUNTIME)
//target表示该注解只能加在类上面
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Autowired {
    //默认该属性必须赋值,不能为null
    boolean required() default true;
}
