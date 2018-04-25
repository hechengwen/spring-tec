package hcw.tecservice.utils;

import hcw.tecservice.service.impl.BaseService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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


    public static Map init(){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/dubbo/consumer.xml");
        context.start();
        cacheMap = Collections.synchronizedMap(new HashMap<String, ClassPathXmlApplicationContext>());
        cacheMap.put("context",context);
        return cacheMap;
    }

//    private static Map<String, ClassPathXmlApplicationContext> initMap = null;

    public static <T> T getRemoteService(Class<T> cls){
        ClassPathXmlApplicationContext context = null;
        if (cacheMap == null) {
            cacheMap = init();
            context = cacheMap.get("context");
            return context.getBean(cls);
        } else {
            context = cacheMap.get("context");
            return context.getBean(cls);
        }
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
