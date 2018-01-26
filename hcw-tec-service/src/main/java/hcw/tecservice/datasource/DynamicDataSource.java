package hcw.tecservice.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/26 11:02
 * Description:若service没有启动事务机制，则执行的顺序为：切面——>determineCurrentLookupKey——>Dao方法
 *             当在service层启动事务时,顺序为：determineCurrentLookupKey——>切面——>Dao方法
 *
 *             根据配置文件中的配置，service层的insert方法，事物传播特性为required（当前有事务则用当前事务，没有则新起一个事物）
 *             如果事务传播特性是required,则会执行doBegin去获取事务,也就是获取数据源
 *             经过以上的分析,可以看出 ,动态数据源只有在没有事务的情况下 ,能正常切换. 不支持分布式事务
 *
 *             一般事务都会在service层加的，如果使用spring的声明式事物管理，那么在调用service层代码之前，spring会通过aop的方式动态添加事务控制代码，
 *             所以如果要想保证事物是有效的，那么必须spring添加事务之前把数据源动态切换过来，也就是动态切换数据源的aop要至少在service上添加，而且要在spring声明式事物aop之前添加。
 *
 * Others:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    public Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getCustomerType();
    }
}
