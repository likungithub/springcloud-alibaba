package com.sonder.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.sonder.domain.Order;
import com.sonder.domain.Product;
import com.sonder.service.OrderService;
import com.sonder.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
@Slf4j
public class OrderController3 {

    @RequestMapping("/order/message1")
    public String message1(){
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2(){
        return "message2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3")//注意这里必须使用这个注解标识,热点规则不生效
    public String message3(String name, Integer age) { return name + age; }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));
    }


}
