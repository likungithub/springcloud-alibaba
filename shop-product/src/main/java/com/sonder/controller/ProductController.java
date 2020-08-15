package com.sonder.controller;

import com.alibaba.fastjson.JSON;
import com.sonder.domain.Product;
import com.sonder.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{pid}")
    public Product product(@PathVariable("pid")Integer pid){
        log.info("接下来要进行{}号商品信息的查询", pid);
        Product product = productService.findById(pid);
        log.info("查询到商品：" + JSON.toJSONString(product));
        return product;
    }

    @RequestMapping("/product/api1/demo1")
    public String demo1(){
        return "demo1";
    }


    @RequestMapping("/product/api1/demo2")
    public String demo2(){
        return "demo2";
    }

    @RequestMapping("/product/api1/demo3")
    public String demo3(){
        return "demo3";
    }

    @RequestMapping("/product/api1/demo4")
    public String demo4(){
        return "demo4";
    }

    @RequestMapping("/product/api1/demo5")
    public String demo5(){
        return "demo5";
    }

    @RequestMapping("/product/api2/demo1")
    public String demo6(){
        return "demo1";
    }


    @RequestMapping("/product/api2/demo2")
    public String demo7(){
        return "demo2";
    }


}
