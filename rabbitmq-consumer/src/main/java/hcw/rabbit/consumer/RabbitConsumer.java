package hcw.rabbit.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import hcw.tec.pojo.User;
import hcw.tec.service.RemoteService;
import hcw.tecservice.service.UserService;
import hcw.tecservice.service.impl.BaseService;
import hcw.tecservice.utils.GetRemoteService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/1/12 15:05
 * Description:
 * Others:
 */
public class RabbitConsumer extends BaseService implements ChannelAwareMessageListener {

    @Autowired
    private UserService userService;

    //    @Override
    public void onMessage(Message message) {
        String data = new String(message.getBody());

        logger.info("listener receive message--------------->:{}\n INFO | messageProperties:{}", data, message.getMessageProperties());
        User user = JSON.parseObject(data, User.class);
        userService.insert(user);
        RemoteService remoteService = GetRemoteService.getRemoteService(RemoteService.class);
        remoteService.testDubbo("");
    }


    /**
     * desc：手动确认消息
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            logger.info("Consumer:[{}]", new String(message.getBody()));
            logger.info("message_propetties:[{}]", message.getMessageProperties());
            // deliveryTag是消息传送的次数，我这里是为了让消息队列的第一个消息到达的时候抛出异常，处理异常让消息重新回到队列，然后再次抛出异常，处理异常拒绝让消息重回队列
            if (message.getMessageProperties().getDeliveryTag() == 1 || message.getMessageProperties().getDeliveryTag() == 2) {
                throw new Exception();
            }

            // multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            e.printStackTrace();
            // 是否再传递
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("消息已重复处理失败,拒绝再次接收...");
                // channel.basicNack 与 channel.basicReject 的区别在于basicNack可以拒绝多条消息，而basicReject一次只能拒绝一条消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
            } else {
                logger.error("消息即将再次返回队列处理...");
                // multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。requeue：被拒绝的是否重新入队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            }
        }

    }
}
