package com.sonder.service.impl;

import com.sonder.dao.OrderDao;
import com.sonder.dao.TxLogDao;
import com.sonder.domain.Order;
import com.sonder.domain.TxLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RocketMQTransactionListener(txProducerGroup = "tx_producer_group")
public class OrderServiceImpl4Listener implements RocketMQLocalTransactionListener {

    @Autowired
    private OrderServiceImpl4 orderServiceImpl4;

    @Autowired
    private TxLogDao txLogDao;


    //执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        String txId = (String) msg.getHeaders().get("txId");

        try{
            Order order = (Order) arg;

            orderServiceImpl4.createOrder(txId,order);

            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }

    //消息回查
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {

        String txId = (String) msg.getHeaders().get("txId");

        TxLog txLog = txLogDao.findById(txId).get();
        if (txLog != null){
            //本地事务（订单）成功了
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            return RocketMQLocalTransactionState.ROLLBACK;
        }

    }
}
