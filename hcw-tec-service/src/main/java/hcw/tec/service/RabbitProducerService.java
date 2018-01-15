package hcw.tec.service;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 15:30
 * Description:
 * Others:
 */
public interface RabbitProducerService {
    /**
     * author:
     * @param routingKey
     * @param message
     */
    void producer(String routingKey, Object message) throws RuntimeException;

    /**
     * author:
     * @param exchange
     * @param routingKey
     * @param message
     */
    void producer(String exchange, String routingKey, Object message) throws RuntimeException;
}
