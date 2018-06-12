package hcw.tecservice.datasource;

import hcw.tecservice.service.MasterDataSourceService;
import hcw.tecservice.service.SlaveDataSourceService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Pointcut("execution(* hcw.tecservice.dao.master.*.*(..))")
    private void masterPoint(){}

    @Pointcut("execution(* hcw.tecservice.dao.slave.*.*(..))")
    private void slavePoint(){}

    @Pointcut("execution(* hcw.tecservice.service.impl.*.*(..))")
    private void service(){}

/*    @Before("slavePoint()")
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
    }*/

    @Around("service()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        if (joinPoint.getTarget() instanceof SlaveDataSourceService) {
            logger.info("使用数据库链接：slave");
            DatabaseContextHolder.setCustomerType("dataSource_slave");
        } else if (joinPoint.getTarget() instanceof MasterDataSourceService) {
            logger.info("使用数据库链接：master");
            DatabaseContextHolder.setCustomerType("dataSource_master");
        }
        return joinPoint.proceed();
    }

}
