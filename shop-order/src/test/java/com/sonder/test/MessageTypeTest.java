package com.sonder.test;

import com.sonder.OrderApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //同步消息
    @Test
    public void testSyncSend(){

        SendResult sendResult = rocketMQTemplate.syncSend("test-topic-1:tag", "这是一条同步消息", 10000);
        System.out.println(sendResult);

    }

    //异步消息
    @Test
    public void testAsyncSend() throws Exception{

        rocketMQTemplate.asyncSend("test-topic-1", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println(e);
            }
        });

        System.out.println("-----------------------");
        Thread.sleep(300000);

    }

    //单向发送
    @Test
    public void testOneWay(){
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.sendOneWay("test-topic-1","这是一条单向消息" + i);
        }

    }

    //单向顺序消息发送
    @Test
    public void testOneWayOrderly(){
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.sendOneWayOrderly("test-topic-1","这是一条单向消息" + i,"xx");
        }

    }



}
