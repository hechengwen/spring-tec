package hcw.rabbit.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 14:44
 * Description:
 * Others:
 */
@Service
public class RabbitProducer {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void convertAndSend(String routingKey,Object message) throws RuntimeException{
        try {
            amqpTemplate.convertAndSend(routingKey,message);
            logger.info("rabbitMQ消息发送成功... - routingKey:[{}],message:[{}]",routingKey,message);
        }catch (AmqpException e) {
            logger.error("rabbitMQ消息发送失败：{}",e.getMessage());
            throw new RuntimeException("rabbitMQ send message failure ...");
        }
    }

    public void convertAndSend(String exchange, String routingKey, Object message)throws RuntimeException{
        try {
            amqpTemplate.convertAndSend(exchange,routingKey,message);
            logger.info("rabbitMQ消息发送成功... - exchange:[{}],routingKey:[{}],message:[{}]",exchange,routingKey,message);
        }catch (AmqpException e) {
            logger.error("rabbitMQ消息发送失败：{}",e.getMessage());
            throw new RuntimeException("rabbitMQ send message failure ...");
        }
    }
}
