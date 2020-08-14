package com.sonder.service.impl;

import com.sonder.dao.OrderDao;
import com.sonder.dao.TxLogDao;
import com.sonder.domain.Order;
import com.sonder.domain.TxLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl4 {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private TxLogDao txLogDao;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //发送半事务消息
    public void createOrderBefore(Order order){

        String txId = UUID.randomUUID().toString();

        rocketMQTemplate.sendMessageInTransaction(
                "tx_producer_group",
                "tx_topic",
                MessageBuilder.withPayload(order).setHeader("txId",txId).build(),
                order);

    }

    //本地事务
    @Transactional
    public void createOrder(String txId,Order order){
        orderDao.save(order);

        //加入事务日志
        TxLog txLog = new TxLog();
        txLog.setTxId(txId);
        txLog.setDate(new Date());

        txLogDao.save(txLog);


    }


}
