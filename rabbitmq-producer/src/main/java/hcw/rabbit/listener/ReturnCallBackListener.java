package hcw.rabbit.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/19 15:24
 * Description:确认消息发送失败后回调
 * Others:
 */
@Component("returnCallBackListener")
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info("message=[{}] - \nreplyCode=[{}] - replyText=[{}] - exchange=[{}] - routingKey=[{}],消息发送失败.....",message,replyCode,replyText,exchange,routingKey);
    }
}
