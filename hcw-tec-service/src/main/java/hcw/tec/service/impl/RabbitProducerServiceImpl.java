package hcw.tec.service.impl;

import hcw.rabbit.producer.RabbitProducer;
import hcw.tec.service.RabbitProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 15:42
 * Description:
 * Others:
 */
@Service
public class RabbitProducerServiceImpl extends BaseService implements RabbitProducerService{

    @Autowired
    private RabbitProducer rabbitProducer;

    @Override
    public void producer(String routingKey, Object message) throws RuntimeException{
        rabbitProducer.convertAndSend(routingKey,message);
    }

    @Override
    public void producer(String exchange, String routingKey, Object message) throws RuntimeException{
        rabbitProducer.convertAndSend(exchange, routingKey, message);
    }
}
