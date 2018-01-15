package hcw.tec.controller;

import com.alibaba.fastjson.JSON;
import hcw.tec.pojo.User;
import hcw.tec.redis.CacheManagerService;
import hcw.tec.service.RabbitProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private RabbitProducerService rabbitProducerService;

    @Autowired
    private CacheManagerService cacheManagerService;

    @RequestMapping(value = "/produceMessage")
    @ResponseBody
    public void produce(User user){
        user.setCreateTime(new Date());
        rabbitProducerService.producer("exchange1","tec.test", JSON.toJSONString(user));
        rabbitProducerService.producer("exchange2","tec.test", JSON.toJSONString(user));
        rabbitProducerService.producer("exchange1","tec.test000", "我很帅！！！");
    }

    @RequestMapping(value = "/redis")
    @ResponseBody
    public void redis(){
        cacheManagerService.set("123","456");
    }

}
