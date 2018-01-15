package hcw.rabbit.consumer;

import hcw.tec.service.impl.BaseService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 15:05
 * Description:
 * Others:
 */
public class RabbitConsumer extends BaseService implements MessageListener{

    @Override
    public void onMessage(Message message) {
        logger.info("listener receive message--------------->:{}\n INFO | messageProperties:{}",new String(message.getBody()),message.getMessageProperties());
    }
}
