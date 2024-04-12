package com.zhouyu.service;


import com.oddeye.*;

@Component("userService")
//如果不加scope注解说明这个class创建bean是单例的(默认)
//@Scope("prototype")//原形bean
public class UserServiceImpl implements BeanNameAware , InitializingBean,UserService {


    @Autowired()
    private OrderService orderService;
    //自动赋值该对象bean id名(@Component("userService"))
    private String beanName;

    public String getProcessorTest() {
        return processorTest;
    }

    public void setProcessorTest(String processorTest) {
        this.processorTest = processorTest;
    }

    private String processorTest;

    public void test(){
        System.out.println("===========================");
        System.out.println(orderService);
        System.out.println("当前beanName:"+beanName);
        System.out.println(processorTest);
        System.out.println("===========================");
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
//        this.beanName = "lyh";

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 在初始化过程中执行InitializingBean接口的方法");
    }
}
