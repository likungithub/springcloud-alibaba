package com.sonder.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class RocketMQReceiveMessageTest {

    //接收消息
    public static void main(String[] args) throws Exception {

        //1.创建消费者，并且制定消费者组的名称
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("myconsumer-group");

        //2.为消费者设置 NameServer的地址
        consumer.setNamesrvAddr("192.168.10.105:9876");

        //3.制定消费者订阅的主题和标签
        consumer.subscribe("myTopic","*");

        //4.设置一个回调函数，并在函数中编写接收消息之后的处理方法
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                //消费逻辑
                System.out.println("Message============>" + list);

                //返回消费成功状态

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //5.启动消息消费者
        consumer.start();
        System.out.println("启动消费者成功了");

    }

}
