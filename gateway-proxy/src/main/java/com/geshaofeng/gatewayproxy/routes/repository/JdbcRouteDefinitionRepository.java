package com.geshaofeng.gatewayproxy.routes.repository;

import com.alibaba.fastjson.JSONObject;
import com.geshaofeng.gatewayproxy.routes.repository.entity.RouteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 实现基于{@link org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository}
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/27
 */
@Component
public class JdbcRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String KEY = "route";

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        ArrayList<RouteDefinition> list = new ArrayList<>();
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(KEY);
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            String key = (String) entry.getKey();
            String json = entries.get(key).toString();
            RouteEntity route = JSONObject.parseObject(json, RouteEntity.class);
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setId(route.getRouteId());
            routeDefinition.setOrder(route.getOrder());
            URI uri = null;
            try {
                uri = new URI(route.getUri());
            } catch (URISyntaxException e) {
                continue;
            }
            routeDefinition.setUri(uri);
            routeDefinition.setFilters(route.getFilters());
            routeDefinition.setPredicates(route.getPredicates());
            list.add(routeDefinition);
        }
        return Flux.fromIterable(list);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.empty();
    }
}
