package hcw.tec.controller;

import hcw.tec.email.service.EmailService;
import hcw.tecservice.annocation.LoginRequired;
import hcw.tecservice.datasource.DatabaseContextHolder;
import hcw.tecservice.globalexception.GlobalException;
import hcw.tec.pojo.User;
import hcw.tecservice.redis.CacheManagerService;
import hcw.rabbit.service.RabbitProducerService;
import hcw.tec.service.RemoteService;
import hcw.tecservice.service.UserService;
import hcw.tecservice.utils.GetRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/email")
    @ResponseBody
    public void email(){
        try {
            // 防止读取配置文件中文乱码问题
            Reader reader = new InputStreamReader(MessageController.class.getClassLoader().getResourceAsStream("email.properties"),"UTF-8");
            System.getProperties().load(reader);
            reader.close();
//            emailService.sendEmail(new String[]{"hechengwen@jumore.com"},new String[]{"809137232@qq.com"},"Test邮件",System.getProperty("email.test.say"),new String[]{"D:\\a\\5041.sm2","D:\\a\\5041.cer"});
            emailService.sendEmail("hechengwen@jumore.com","809137232@qq.com","我的最大",System.getProperty("email.test.say"),new String[]{"D:\\a\\5041.sm2","D:\\a\\5041.cer"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/produceMessage")
    @ResponseBody
    public void produce(User user) {
        user.setCreateTime(new Date());
        rabbitProducerService.producer("exchange1", "tec.test", "tec.test");
        rabbitProducerService.producer("exchange2", "tec.test123123", "tec.test123123");
        rabbitProducerService.producer("tec.test111", "tec.test111");
    }

    @RequestMapping(value = "/redis")
    @ResponseBody
    @LoginRequired
    public void redis() {
        cacheManagerService.set("123", "456");
        logger.error("date:{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
        logger.info("redis.........");
    }


    @RequestMapping(value = "/dubbo")
    @ResponseBody
    public void dubbo() {
        RemoteService remoteService = GetRemoteService.getRemoteService(RemoteService.class);
        remoteService.testDubbo("");
        logger.info("");
    }

    @RequestMapping(value = "/exception")
    @ResponseBody
    public String exception() throws RuntimeException {
        throw new RuntimeException();
    }

    @RequestMapping(value = "/myexception")
    @ResponseBody
    public String myexception() throws GlobalException {
        throw new GlobalException("我是自己定义的异常.....");
    }

    @RequestMapping(value = "/nullpoint")
    @ResponseBody
    public String nullpoint() throws Exception {
        throw new Exception("我是大异常.....");
    }

    @RequestMapping("pressure")
    public void pressureTest() {
        User user = new User();
        user.setMobile("15497863");
        user.setUserName("zhangsan");
        user.setPassword("7987163431");
        DatabaseContextHolder.setCustomerType("dataSource_master");
        userService.insert(user);

        user.setCreateTime(new Date());
        user.setEmail("hechengwen@jumore.com");
        DatabaseContextHolder.setCustomerType("dataSource_slave");
        userService.insertSlave(user);
    }
}
