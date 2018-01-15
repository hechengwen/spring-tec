package hcw.tec.utils;

import hcw.tec.service.impl.BaseService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/15 14:18
 * Description:
 * Others:
 */
public class GetRemoteService extends BaseService {

    private static Map<String, ClassPathXmlApplicationContext> cacheMap = null;

    static {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        cacheMap = Collections.synchronizedMap(new HashMap<String, ClassPathXmlApplicationContext>());
        cacheMap.put("context",context);
    }

//    private static Map<String, ClassPathXmlApplicationContext> initMap = null;

    public static <T> T getRemoteService(Class<T> cls){
        ClassPathXmlApplicationContext context = null;
        if (cacheMap != null) {
            context = cacheMap.get("context");
            return context.getBean(cls);
        }
        return null;
    }

    /*@Override
    public void afterPropertiesSet() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
        logger.info("消费dubbo服务start.....\ncontext=[{}]",context.getBeanFactory());
        if (initMap == null) {
            initMap = Collections.synchronizedMap(new HashMap<String, ClassPathXmlApplicationContext>());
            initMap.put("context",context);
        }
    }*/
}
