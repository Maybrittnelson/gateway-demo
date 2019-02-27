package com.geshaofeng.gatewayproxy;

import com.geshaofeng.gatewayproxy.config.GatewayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//2.Ribbon使用自定义规则
@RibbonClients(defaultConfiguration = GatewayConfig.class)
//2.不加此注释，直接执行fallback方法😔
@EnableFeignClients
public class GatewayProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayProxyApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
/*        return builder.routes()
                .route(r -> r.path("/user/**").uri("lb://user-service-provider"))
                .build();*/
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/post")
                        .uri("http://httpbin.org/"))
                .build();
    }


}
