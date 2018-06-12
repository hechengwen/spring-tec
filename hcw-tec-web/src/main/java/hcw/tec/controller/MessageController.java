package hcw.tec.controller;

import hcw.rabbit.service.RabbitProducerService;
import hcw.tec.email.service.EmailService;
import hcw.tec.pojo.User;
import hcw.tec.service.RemoteService;
import hcw.tecservice.annocation.LoginRequired;
import hcw.tecservice.datasource.DatabaseContextHolder;
import hcw.tecservice.learn.ftp.FTPSync;
import hcw.tecservice.globalexception.GlobalException;
import hcw.tecservice.redis.CacheManagerService;
import hcw.tecservice.service.SlaveUserService;
import hcw.tecservice.service.UserService;
import hcw.tecservice.learn.ssl.HttpsRequest;
import hcw.tecservice.utils.GetRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

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

//    @Autowired
//    private hcw.tec.service.UserService userService1;

    @Autowired
    private EmailService emailService;

//    @Autowired
//    private RemoteService remoteService;

    @Autowired
    private FTPSync ftpSync;

    @RequestMapping("ftp")
    public void ftp() {
        try {
            ftpSync.uploadDirectory("D:\\logs", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/sslTest")
    @ResponseBody
    public String sslTest(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            logger.info("cookie name: [{}] ,cookie value : [{}]", cookie.getName(), cookie.getValue());
        }
        String result = HttpsRequest.httpsRequest("https://kyfw.12306.cn/", "GET", "666");

        String html = HttpsRequest.extractText(result);
        logger.info("result : {}", result);
        logger.info("html : {}", html);

        return html;
    }

    @RequestMapping(value = "/email")
    @ResponseBody
    public void email() {
        try {
            // 防止读取配置文件中文乱码问题
            Reader reader = new InputStreamReader(MessageController.class.getClassLoader().getResourceAsStream("email.test.say"), "UTF-8");
            System.getProperties().load(reader);
            reader.close();
//            emailService.sendEmail(new String[]{"hechengwen@jumore.com"},new String[]{"809137232@qq.com"},"Test邮件",System.getProperty("email.test.say"),new String[]{"D:\\a\\5041.sm2","D:\\a\\5041.cer"});
            emailService.sendEmail("hechengwen@jumore.com", "809137232@qq.com", "我的最大", System.getProperty("email.test.say"), new String[]{"D:\\a\\5041.sm2", "D:\\a\\5041.cer"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/produceMessage")
    @ResponseBody
    public void produce(User user) {
        user.setCreateTime(new Date());
        user.setEmail("hangzhou@qq.com");
        user.setPassword("123456");
        user.setUserName("hehe");
//        user.setMobile("17710363894");
        rabbitProducerService.producer("exchange3", "rabbit.10002", user);
//        rabbitProducerService.producer("exchange2", "tec.test", "tec.test123123");
//        rabbitProducerService.producer("tec.test111", "tec.test111");
    }

    @RequestMapping(value = "/redis")
    @ResponseBody
    @LoginRequired
    public void redis() {
        cacheManagerService.set("name", "10000");
        logger.error("date:{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()));
        logger.info("redis.........");

        List list = new ArrayList();
        list.add("I Love You");
        list.add("12");
        list.add("hello world");
        Map map = new HashMap();
        map.put("name","zhangsan");
        map.put("age","23");
        map.put("hobby","young gril");
        cacheManagerService.setStr("list_test",list,300L);
        cacheManagerService.setStr("map_test",map,300L);
        List list2 = (List) cacheManagerService.getKeyList("list_test",0l,-1l);
        Map map2 = (Map) cacheManagerService.getKeyMap("map_test");
        cacheManagerService.delKey("list_test");
        cacheManagerService.delKey("map_test");

    }


    @RequestMapping(value = "/dubbo", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String dubbo() {
        RemoteService remoteService = GetRemoteService.getRemoteService(RemoteService.class);
        String result = remoteService.testDubbo("hechengwen");
        for (Method method : RemoteService.class.getMethods()) {
            result = method.getName() + " : " + result;
        }
        logger.info(result);

        hcw.tec.service.UserService userService = GetRemoteService.getRemoteService(hcw.tec.service.UserService.class);
        String result1 = userService.getUser("hechengwen");
        logger.info("{}", result1);
        String data = result + result1;
        return result;
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

    @Autowired
    private SlaveUserService slaveUserService;

    @RequestMapping("pressure")
    public void pressureTest() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程名：" + Thread.currentThread().getName());
                for (int i = 2; i <= 1000; i++) {
                    final int finalI = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User();
                            user.setMobile("15497863");
                            user.setUserName("zhangsan");
                            user.setPassword("7987163431");
                            user.setIdCard("thread = " + finalI);
                            userService.insert(user);

                            System.out.println("插入返回主键：" + user.getUserId());

                            user.setCreateTime(new Date());
                            user.setEmail("hechengwen@jumore.com");
                            slaveUserService.insert(user);
                        }
                    }, "t" + i).start();
                }
            }
        }, "t1");
        thread.start();
    }
}
