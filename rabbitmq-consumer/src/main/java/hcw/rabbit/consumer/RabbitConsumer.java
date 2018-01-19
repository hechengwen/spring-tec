package hcw.rabbit.consumer;

import com.alibaba.fastjson.JSON;
import hcw.tec.pojo.User;
import hcw.tec.service.RemoteService;
import hcw.tecservice.service.UserService;
import hcw.tecservice.service.impl.BaseService;
import hcw.tecservice.utils.GetRemoteService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 15:05
 * Description:
 * Others:
 */
public class RabbitConsumer extends BaseService implements MessageListener{

    @Autowired
    private UserService userService;

    @Override
    public void onMessage(Message message) {
        String data = new String(message.getBody());
        logger.info("listener receive message--------------->:{}\n INFO | messageProperties:{}",data,message.getMessageProperties());
        User user = JSON.parseObject(data,User.class);
        userService.insert(user);
        RemoteService remoteService = GetRemoteService.getRemoteService(RemoteService.class);
        remoteService.testDubbo("");
    }
}
