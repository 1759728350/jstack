package com.oddeye;
/*
 **description: 在创建bean的过程时会调用这个接口里的方法
 *              常用于验证属性值,赋值,修改属性值
 **param&method:
 **caller: ZhouyuApplicationContext =>Object createBean(String beanName,BeanDefinition beanDefinition)
 **problems:
 */

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
