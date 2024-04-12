package com.oddeye;

public class BeanDefinition {
    //clazz用于ZhouyuApplicationContext.createBean(beanDefinition)方法创建对象用
    private Class clazz;
    private String scope;



    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
