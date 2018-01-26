package hcw.tecservice.datasource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/26 11:08
 * Description:
 * Others:
 */
@Component
@Aspect
public class DataSourceAop {

    @Pointcut("execution(* hcw.tecservice.dao.master.*.*(..))")
    private void masterPoint(){}

    @Pointcut("execution(* hcw.tecservice.dao.slave.*.*(..))")
    private void slavePoint(){}


    @Before("slavePoint()")
    public void setDataSourceSlave() {
        DatabaseContextHolder.setCustomerType("dataSource_slave");
    }

    @Before("masterPoint()")
    public void setDataSourceMaster() {
        DatabaseContextHolder.setCustomerType("dataSource_master");
    }

    @After("execution(* hcw.tecservice.dao.master.*.*(..)) || execution(* hcw.tecservice.dao.slave.*.*(..))")
    public void clearCustomerType(){
        DatabaseContextHolder.clearCustomerType();
    }
}
