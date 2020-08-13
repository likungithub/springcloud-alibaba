package com.sonder.service;

import com.sonder.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "shop-user",topic = "order-topic") //消费组名、消费主题
public class SmsService implements RocketMQListener<Order> {

    //消费逻辑
    @Override
    public void onMessage(Order message) {
        log.info("接收到了一个订单信息{}，接下来就可以发送短信通知了",message);
    }
}
