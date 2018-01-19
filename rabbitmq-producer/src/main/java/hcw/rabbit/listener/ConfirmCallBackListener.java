package hcw.rabbit.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/19 15:23
 * Description:确认消息发送成功后回调
 * Others:
 */
@Component("confirmCallBackListener")
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("correlationData=[{}] - ack=[{}] - cause=[{}] - 消息发送成功.....",correlationData,ack,cause);
    }
}
