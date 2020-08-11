package com.sonder.service;

import com.sonder.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product")  //指定调用nacos下那个微服务
public interface ProductService {

    @RequestMapping("/product/{pid}") //指定请求的URI部分
    Product findByPid(@PathVariable("pid") Integer pid);

}
