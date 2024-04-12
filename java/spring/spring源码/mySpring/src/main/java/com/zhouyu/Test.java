package com.zhouyu;

import com.oddeye.ZhouyuApplicationContext;
import com.zhouyu.service.UserService;
import com.zhouyu.service.UserServiceImpl;

public class Test {

    public static void main(String[] args) {
        ZhouyuApplicationContext applicationContext = new ZhouyuApplicationContext(AppConfig.class);
        UserService userServiceImpl = (UserService) applicationContext.getBean("userService");
        userServiceImpl.test();


    }

}
