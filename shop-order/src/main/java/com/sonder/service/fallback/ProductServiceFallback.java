package com.sonder.service.fallback;

import com.sonder.domain.Product;
import com.sonder.service.ProductService;
import org.springframework.stereotype.Service;

//这是一个容错类，需要实现feign所在的接口，并实现接口中的所有方法
//一旦调用feign方法出现异常，就会进入当前类中同名方法，执行容错逻辑
@Service
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findByPid(Integer pid) {

        //容错逻辑
        Product product = new Product();
        product.setPid(-100);
        product.setPname("远程调用feign接口出现异常，进入了容错方法");


        return product;
    }
}
