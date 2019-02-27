package com.geshaofeng.gatewayproxy.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Gsealy
 * @date 2019/1/7 10:20
 */
@Component
public class VerifyGatewayFilter extends AbstractGlobalFilter implements Ordered {

    private static final Log log = LogFactory.getLog(VerifyGatewayFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getHeaders().getContentType() != null) {
            String raw = toRaw(exchange);
            log.info(raw);
        }
        System.out.println(222);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 100;
    }

}
