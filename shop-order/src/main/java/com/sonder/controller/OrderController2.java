package com.sonder.controller;

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
public class OrderController2 {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/order2/prod/{pid}")
    public Order product(@PathVariable("pid")Integer pid){
        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");

        /*String url = "http://service-product/product/" + pid;
        Product product = restTemplate.getForObject(url, Product.class);  //通过restTemplate调用*/

        Product product = productService.findByPid(pid);

        try {
            Thread.sleep(20001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(">>商品信息，查询结果：" + JSON.toJSONString(product));

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");

        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);

        orderService.save(order);

        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));

        return order;
    }

    //测试高并发
    @RequestMapping("/order2/message")
    public String message(){
        return "测试高并发";
    }


}
