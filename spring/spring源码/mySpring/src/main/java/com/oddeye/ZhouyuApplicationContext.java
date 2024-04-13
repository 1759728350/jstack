package com.oddeye;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZhouyuApplicationContext {
    // 容器中的bean配置文件,是一个类,不是像往常用的xml文件
    private Class configClass;
    //创建一个单例池
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    //spring扫描到的所有bean的beanDefinition对象    
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();


    /**
     * @Description: 扫描并创建bean
     * @Param: configClass
     * @return:
     * @caller: 主程序main中或Test中
     * @Author: Mr.oddEye  @Date: 2022/3/5-16:34
     * @KnowledgeProblem: no problem?
     */
    public ZhouyuApplicationContext(Class configClass) {
        this.configClass = configClass;

        scan(configClass);

        for (Map.Entry<String,BeanDefinition> beanDefinitionEntry: beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            BeanDefinition beanDefinition = beanDefinitionEntry.getValue();
            if (beanDefinition.getScope().equals("singleton")){
                //创建单例bean
                Object bean = createBean(beanName,beanDefinition);
                //放入单例池
                singletonObjects.put(beanName,bean);

            }
        }
    }


    /**
     * @Description: 用beanDefinition创建bean
     *               从beanDefinition中获取bean的类名,通过反射创建一个新的bean
     *               然后依据autowire注入属性对象
     * @Param: beanDefinition ioc创建对象时需要的信息
     * @return: instance 创建对象
     * @caller: ZhouyuApplicationContext(Class configClass)
     *          Object getBeans(String beanName)
     * @Author: Mr.oddEye  @Date: 2022/3/5-16:24
     * @KnowledgeProblem: no problem?
     *       反射创建bean对象
     */
    public Object createBean(String beanName,BeanDefinition beanDefinition){
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            //属性赋值,属性注入
            for (Field declaredField : clazz.getDeclaredFields()) {
                //如果这个属性上有autoWired注解,则赋值
                if (declaredField.isAnnotationPresent(Autowired.class)){
                    //获取属性(对象)的bean,因此必须先将属性所用到的bean扫描并生成对应的beanDefinition
                    // 并已经加入到beanDefinitionMap中
                    Object bean = getBean(declaredField.getName());
                    //ioc里如果没有
                    if (bean == null){
                        Autowired autowiredAnnotation = declaredField.getDeclaredAnnotation(Autowired.class);
                        //如果该值不能为空,则抛出异常
                        if (autowiredAnnotation.required()){
                            throw new RuntimeException("自动注入失败,请检查"+declaredField.getName()+"属性是否有对应的bean");
                        }
                    }
                    //抑制反射检查
                    declaredField.setAccessible(true);
                    //将引用类型的bean赋值给该属性,注入
                    declaredField.set(instance,bean);
                }
            }
            //Aware回调
            //如果该实例实现了BeanNameAware接口,则调用setBeanName方法,将beanName设置为
            if (instance instanceof BeanNameAware){
                //自动为对象赋值bean名字
                ((BeanNameAware) instance).setBeanName(beanName);
            }
            //到List里遍历对象的前置处理器
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                //注意:这个instance已经不是之前的那个引用了
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

            }
            //初始化
            if (instance instanceof InitializingBean){
                try {
                    ((InitializingBean) instance).afterPropertiesSet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //后置处理器
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {

                instance = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            }

            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: 根据ComponentScan注解扫描包下的所有类,将这些类加入到类加载器中
     *              然后扫描是否有component注解,如果有就生成对应的beanDefinition
     *              再看是否为单例后将beanDefinition放入beanDefinitionMap中
     * @Param: configClass 配置文件类
     * @return:
     * @caller: ZhouyuApplicationContext(Class configClass)
     * @Author: Mr.oddEye  @Date: 2022/3/5-16:07
     * @KnowledgeProblem: no problem?
     *     获取包下的所有类以及对路径进行剪裁拼接
     *     类加载器加载类
     *     classpath相对路径
     */
    private void scan(Class configClass) {
        //获取componentScan注解
        ComponentScan componentScanAnnotation
                = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        //获取该注解所接收的value值
        String path = componentScanAnnotation.value();
        path = path.replace(".","/");
        //根据包名获取到该包下的所有类
        //类加载器(有三种,暂时不展开)
        //这个获取到的classload是在当前app下的classload
        ClassLoader classLoader = ZhouyuApplicationContext.class.getClassLoader();
        //用类加载器从文件夹来拿资源,填的是相对路径,相对于classpath,就是target下的classes文件夹
        //classpath:  D:\project\mySpring\target\classes
        //com.zhouyu.service
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        //判断是否是文件夹
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println(f.getAbsolutePath());
                //输出D:\project\mySpring\target\classes\com\zhouyu\service\UserService.class

                //判断是否是class文件
                if(f.getName().endsWith(".class")){
                    //获取class对象
                    //那么如何将上面那个路径转化为相对于classpath的路径呢?
                    String fileName = f.getAbsolutePath();
                    //将从com(包括com)到.class(不包括)的字符串截出来
                    String className = fileName.substring(fileName.indexOf("com"),fileName.indexOf(".class"));
                    System.out.println(className);
                    //输出com\zhouyu\service\UserService
                    //将"\"替换为"." 此处要转义所以为"\\"
                    className = className.replace("\\",".");
                    System.out.println(className);
                    //输出com.zhouyu.service.UserService

                    Class<?> clazz = null;
                    try {
                        clazz = classLoader.loadClass(className);
                        System.out.println("clazz   "+clazz);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    //判断这个类上面是否有component注解
                    if (clazz.isAnnotationPresent(Component.class)) {
                        System.out.println("扫描到component注解");
                        //有注解说明当前这个类是个bean
                        //现在获取到了要创建bean的class   class--->bean
                        //那么是每一个class都需要创建为bean吗?
                        //不是,还要根据scope这个注解
                        //判断这个bean是单例bean还是原形bean
                        //此处要用到BeanDefinition 解析class


                        //扫描处理器
                        //判断这个class是否为后置处理器/这个类是否实现了BeanPostProcessor接口
                        if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                            try {
                                //将这些处理器创建对象并存入beanPostProcessorList
                                BeanPostProcessor beanPostProcessorInstance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                                beanPostProcessorList.add(beanPostProcessorInstance);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }

                        }
                        //BeanDefinition 解析class
                        BeanDefinition beanDefinition = new BeanDefinition();
                        //将该class放入到beanDefinition中
                        beanDefinition.setClazz(clazz);

                        //判断该类是否加了scope注解
                        //根据scope注解来修改设置beanDefinition的scope状态
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            //获取scope注解
                            Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                            //设置beanDefinition的scope属性为所设置的scope注解参数
                            beanDefinition.setScope(scopeAnnotation.value());
                        }else {
                            //没有加scope注解,默认设置beanDefinition的scope属性为单例
                            beanDefinition.setScope("singleton");
                        }

                        //获取component注解中的参数:beanName用于后面存beanDefinitionMap
                        Component ComponentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                        String beanName = ComponentAnnotation.value();

                        //将扫描的beanDefinition存放到map中
                        beanDefinitionMap.put(beanName,beanDefinition);
                    }
                }
            }
        }
    }


    /**
     * @Description: getBean在识别到为单例时不涉及创建bean过程,原形才会创建新bean
     * @Param: beanName
     * @return: singletonBean or prototypeBean or 不存在该bean
     * @caller: 主程序main中或Test中
     * @Author: Mr.oddEye  @Date: 2022/3/5-16:27
     * @KnowledgeProblem: no problem?
     */
    public Object getBean(String beanName){
        //判断beanDefinitionMap中是否有对应beanName的beanDefinition
        if(beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")){
                //返回单例池中对应beanName的bean
                Object singletonBean = singletonObjects.get(beanName);
                return singletonBean;
            }else {
                //是原形bean,重新创建一个新的bean
                Object prototypeBean = createBean(beanName,beanDefinition);
                return prototypeBean;
            }
        }else{
            throw new RuntimeException("不存在"+beanName+"这个bean");
        }
    }
}
