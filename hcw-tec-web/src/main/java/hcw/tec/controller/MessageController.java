package hcw.tec.controller;

import com.alibaba.fastjson.JSON;
import hcw.tecservice.annocation.LoginRequired;
import hcw.tecservice.globalexception.GlobalException;
import hcw.tec.pojo.User;
import hcw.tecservice.redis.CacheManagerService;
import hcw.rabbit.service.RabbitProducerService;
import hcw.tec.service.RemoteService;
import hcw.tecservice.utils.GetRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 16:35
 * Description:
 * Others:
 */
@Controller
@RequestMapping(value = "message")
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitProducerService rabbitProducerService;

    @Autowired
    private CacheManagerService cacheManagerService;

    @RequestMapping(value = "/produceMessage")
    @ResponseBody
    public void produce(User user){
        user.setCreateTime(new Date());
        rabbitProducerService.producer("exchange1","tec.test", "tec.test");
        rabbitProducerService.producer("exchange2","tec.test123123", "tec.test123123");
        rabbitProducerService.producer("tec.test111", "tec.test111");
    }

    @RequestMapping(value = "/redis")
    @ResponseBody
    @LoginRequired
    public void redis(){
        cacheManagerService.set("123","456");
        logger.error("date:{}",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
        logger.info("redis.........");
    }


    @RequestMapping(value = "/dubbo")
    @ResponseBody
    public void dubbo(){
        RemoteService remoteService = GetRemoteService.getRemoteService(RemoteService.class);
        remoteService.testDubbo("");
        logger.info("");
    }

    @RequestMapping(value = "/exception")
    @ResponseBody
    public String exception() throws RuntimeException{
        throw new RuntimeException();
    }

    @RequestMapping(value = "/myexception")
    @ResponseBody
    public String myexception() throws GlobalException{
        throw new GlobalException("我是自己定义的异常.....");
    }

    @RequestMapping(value = "/nullpoint")
    @ResponseBody
    public String nullpoint() throws Exception{
        throw new Exception("我是大异常.....");
    }
}
