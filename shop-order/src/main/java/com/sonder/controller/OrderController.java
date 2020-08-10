package com.sonder.controller;

import com.alibaba.fastjson.JSON;
import com.sonder.domain.Order;
import com.sonder.domain.Product;
import com.sonder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/prod/{pid}")
    public Order product(@PathVariable("pid")Integer pid){
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");

        //通过restTemplate调用商品微服务
        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);

        log.info(">>商品信息，查询结果：" + JSON.toJSONString(product));

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());

        orderService.save(order);

        return order;
    }

}
