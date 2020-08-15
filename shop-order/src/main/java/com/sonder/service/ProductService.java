package com.sonder.service;

import com.sonder.domain.Product;
import com.sonder.service.fallback.ProductServiceFallback;
import com.sonder.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * fallback指定容错类
 */
@FeignClient(value = "service-product",/*fallback = ProductServiceFallback.class,*/fallbackFactory = ProductServiceFallbackFactory.class)  //指定调用nacos下那个微服务
public interface ProductService {

    @RequestMapping("/product/{pid}") //指定请求的URI部分
    Product findByPid(@PathVariable("pid") Integer pid);

}
