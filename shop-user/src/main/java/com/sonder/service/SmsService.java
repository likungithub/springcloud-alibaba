package com.sonder.service;

import com.alibaba.fastjson.JSON;
import com.sonder.dao.UserDao;
import com.sonder.domain.Order;
import com.sonder.domain.User;
import com.sonder.util.SmsUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service("shopSmsService")
@RocketMQMessageListener(consumerGroup = "shop-user",
        topic = "order-topic",
        consumeMode = ConsumeMode.CONCURRENTLY,
        messageModel = MessageModel.CLUSTERING) //消费组名、消费主题、消费模式（同步消费（无序）、顺序消费）、消息类型（集群和广播）
public class SmsService implements RocketMQListener<Order> {

    @Autowired
    private UserDao userDao;

    //消费逻辑
    @Override
    public void onMessage(Order message) {
        log.info("接收到了一个订单信息{}，接下来就可以发送短信通知了",message);

        //获取手机号（根据uid）
        User user = userDao.findById(message.getUid()).get();

        //生成验证码
        StringBuilder smsCode = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            smsCode.append(new Random(9).nextInt() + 1);
        }

        Param param = new Param(smsCode.toString());


        //开始发送短信
        SmsUtil.sendSms(user.getTelephone(),"signName","templateCode", JSON.toJSONString(smsCode));

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Param{
        private String code;
    }

}
