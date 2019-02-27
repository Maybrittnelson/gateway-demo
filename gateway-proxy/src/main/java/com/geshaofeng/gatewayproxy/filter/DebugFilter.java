package com.geshaofeng.gatewayproxy.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/22
 */
//@Component
public class DebugFilter extends AbstractGlobalFilter implements Ordered {

    private final Logger logger = LoggerFactory.getLogger(DebugFilter.class);

    @Value("${debug}")
    private boolean debug;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (debug) {
            ServerHttpRequest request = exchange.getRequest();
            String body = "";
            if (request.getHeaders().getContentType() != null) {
                body = toRaw(exchange);
            }
            String url = request.getURI().toString() + "?" + request.getQueryParams().toString();
            logger.debug("request url:{}, method:{}, body:{}", url, request.getMethod(), body);
        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 101;
    }
}
