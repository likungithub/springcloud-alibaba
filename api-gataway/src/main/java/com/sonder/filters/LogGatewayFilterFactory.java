package com.sonder.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义局部过滤器
 */
//@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    //过滤器逻辑
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("开始执行过滤器逻辑......");
                if (config.consoleLog){
                    System.out.println("consoleLog已经开启了......");
                }
                if (config.cacheLog){
                    System.out.println("cacheLog已经开启了......");
                }

                return chain.filter(exchange);
            }
        };
    }

    //读取配置文件中的参数赋值到配置类中
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;
    }
}
