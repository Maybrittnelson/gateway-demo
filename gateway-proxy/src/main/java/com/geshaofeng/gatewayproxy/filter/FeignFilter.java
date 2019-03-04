package com.geshaofeng.gatewayproxy.filter;

import com.alibaba.fastjson.JSON;
import com.geshaofeng.gatewayproxy.user.UserService;
import com.geshaofeng.gatewayproxy.user.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/22
 */
//@Component
public class FeignFilter implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(FeignFilter.class);

    @Autowired
    private UserService userService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<User> all = userService.findAll();
        logger.debug("find all users:{}", all);
        if (all == null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            HashMap<String, String> map = new HashMap<>();
            map.put("code", "500");
            map.put("message", "查询用户信息失败");
            String s = JSON.toJSONString(map);
            exchange.getResponse().getHeaders().set("Content-Type", "application/json;charset=UTF-8");
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(s.getBytes(StandardCharsets.UTF_8));
            return exchange.getResponse().writeWith(Flux.just(buffer));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
