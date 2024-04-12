package com.oddeye;
/*
 **description: 实现该接口的类会自动赋值该类的beanName属性
 **param&method:
 **caller: 1.ZhouyuApplicationContext  public Object createBean
 *              (String beanName,BeanDefinition beanDefinition)
 *          2.ZhouyuBeanPostProcessor Object postProcessBeforeInitialization(Object bean, String beanName)
 **problems:
 */
public interface BeanNameAware {
    void setBeanName(String beanName);
}
