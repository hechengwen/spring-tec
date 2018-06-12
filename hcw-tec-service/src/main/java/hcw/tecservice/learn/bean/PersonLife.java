package hcw.tecservice.learn.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/29 15:23
 * Description:
 * Others:
 */
public class PersonLife implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean, ApplicationContextAware {

    private String name;

    /**
     * Gets the value of name.
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * <p>
     * <p>You can use getName() to get the value of name</p>
     *
     * @param name name
     */
    public void setName(String name) {
        System.out.println("二【set方法】调用set方法，注入属性值:" + name);
        this.name = name;
    }

    public PersonLife() {
        System.out.println("一【构造方法】调用构造方法");
    }

    public void initMethod(){
        System.out.println("七【init-method】调用<bean>的init-method属性指定的初始化方法");
    }

    public void destoryMethod(){
        System.out.println("九【destroy-method】调用<bean>的destroy-method属性指定的初始化方法");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("四【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory(),BeanFactory = " + beanFactory);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("三【BeanNameAware接口】调用BeanNameAware.setBeanName()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("八【DiposibleBean接口】调用DiposibleBean.destory()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("六【InitializingBean接口】调用InitializingBean.afterPropertiesSet()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("五【ApplicationContextAware接口】调用ApplicationContextAware.setApplicationContext方法,ApplicationContext = " + applicationContext);
    }
}
