package com.sonder.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.BeforeRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 这是一个自定义的路由断言工厂，要求有两个
 * 1.名字必须是  配置+RoutePredicateFactory
 * 2.必须继承AbstractRoutePredicateFactory
 */
//@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    //构造函数
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    //读取配置文件中的参数值，配置到配置类的属性上
    public List<String> shortcutFieldOrder() {
        //这个位置的顺序必须和配置文件的顺序一样
        return Arrays.asList("minAge","maxAge");
    }

    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //接收前台传入的参数
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");

                //1.先判断是否为空
                if (StringUtils.isNotEmpty(ageStr)){
                    //2.如果不为空，再进行路由逻辑判断
                    int age = Integer.parseInt(ageStr);
                    if (age < config.maxAge && age > config.minAge){
                        return true;
                    }else {
                        return false;
                    }
                }
                return false;
            }
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {

        private int minAge;

        private int maxAge;

    }

}
