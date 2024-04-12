package com.oddeye;
/*
 **description: 在对象初花前和初始化后加入切片
 **param&method:
 **caller:  ZhouyuApplicationContext =>Object createBean(String beanName,BeanDefinition beanDefinition)
 **problems:
 */

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName);
    Object postProcessAfterInitialization(Object bean, String beanName);
}


