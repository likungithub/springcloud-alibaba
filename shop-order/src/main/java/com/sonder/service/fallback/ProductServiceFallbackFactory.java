package com.sonder.service.fallback;

import com.sonder.domain.Product;
import com.sonder.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//这是一个容错类，实现一个FallbackFactory接口
@Service
@Slf4j
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    //这就是feign在调用过程中产生的具体异常 Throwable
    @Override
    public ProductService create(Throwable throwable) {

        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {

                log.error("{}",throwable);
                //容错逻辑
                Product product = new Product();
                product.setPid(-100);
                product.setPname("远程调用feign接口出现异常，进入了容错方法");

                return product;
            }
        };
    }
}
