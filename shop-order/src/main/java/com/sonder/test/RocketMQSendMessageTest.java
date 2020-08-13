package com.sonder.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class RocketMQSendMessageTest {

    //发送消息
    public static void main(String[] args) throws Exception {

        //1.创建消息生产者，并设置生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");

        //2.为生产者设置NameServer的地址
        producer.setNamesrvAddr("192.168.10.105:9876");

        //3.启动生产者
        producer.start();

        //4.构建消息对象，主要是设置消息的主题 标签 内容
        Message message = new Message("myTopic", "myTag", ("Test RocketMQ Message").getBytes());

        //5.发送消息
        SendResult sendResult = producer.send(message, 10000);
        System.out.println(sendResult);

        //6.关闭生产者
        producer.shutdown();


    }

}
