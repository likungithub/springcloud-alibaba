package com.sonder.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sonder.dao.OrderDao;
import com.sonder.domain.Order;
import com.sonder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl3{


    /**
     * blockHandler指定发生BlockException时进入的方法
     * fallback指定发生Throwable时进入的方法
     * @param name
     * @return
     */
    @SentinelResource(value = "message",blockHandler = "blockHandler",fallback = "fallback")
    public String message(String name) {
        return "message";
    }

    public String blockHandler(String name, BlockException e){
        //自定义异常逻辑
        log.error("触发了BolckException,内容为{}", e);
        return "BlockException";
    }

    public String fallback(String name, Throwable e){
        //自定义异常逻辑
        log.error("fallback,内容为{}", e);
        return "BlockException";
    }

}
