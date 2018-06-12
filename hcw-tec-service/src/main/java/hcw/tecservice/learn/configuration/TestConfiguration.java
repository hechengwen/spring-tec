package hcw.tecservice.learn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/23 14:25
 * Description:
 * @Configuation等价于<Beans></Beans>
   @Bean等价于<Bean></Bean>
   @ComponentScan等价于<context:component-scan base-package="com.dxz.demo"/>
 * Others:
 */
@Configuration
@ComponentScan(basePackages = "hcw.tecservice.learn.configuration")
public class TestConfiguration {

    public TestConfiguration(){
        System.out.println("TestConfiguration容器启动初始化。。。");
    }


    /**
     * 如果未通过@Bean(name = "")指定bean的名称，则默认与标注的方法名相同
     * @return
     */
/*    @Bean(name = "testbean",initMethod = "start",destroyMethod = "cleanUp")
    @Scope("prototype")
    public TestBean testBean(){
        return new TestBean();
    }*/
}
