package com.geshaofeng.gatewayproxy.routes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 实现基于{@link org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository}
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/27
 */
public class JdbcRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return null;
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
