package com.zhouyu.service;/*
 **description: 在spring源码中开启aop就是将切片加入到后置处理器中
                各种框架也因此而能和spring整合
 **param&method:
 **caller:
 **problems:
 */

import com.oddeye.BeanPostProcessor;
import com.oddeye.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


@Component
public class ZhouyuBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("everything初始化前");
        if (beanName.equals("userService")) {

            ((UserServiceImpl) bean).setProcessorTest("在前面随便切");
        }
        return bean;
    }

    /**
     * @Description: 在后置处理器中加aop切片
     *               在spring源码中开启aop就是将切片加入到后置处理器中
     * @Param:
     * @return:
     * @caller:
     * @Author: Mr.oddEye  @Date: 2022/3/6-15:41
     * @KnowledgeProblem: no problem?
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (beanName.equals("userService")) {
            System.out.println("userService初始化后");


            Object proxyInstance = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("aop代理逻辑");

                    return method.invoke(bean, args);
                }

            });
            return proxyInstance;
        }
        //出userService的其他类不用代理处理
        return bean;
//        return proxyInstance;
    }
}
