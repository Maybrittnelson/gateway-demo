package com.geshaofeng.gatewayproxy.routes.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
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
//@Component
public class JdbcRouteDefinitionRepository implements RouteDefinitionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ReactiveRedisTemplate<String, String> stringReactiveRedisTemplate;

    public static final String KEY = "route";

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
/*        factory.getReactiveConnection().serverCommands().flushAll().thenMany(
                routeOps.opsForHash().entries(KEY)
        ).subscribe(entries -> {
            Object key = entries.getKey();

        });
        ArrayList<RouteDefinition> list = new ArrayList<>();
        Map<Object, Object> entries = routeOps.opsForHash().entries(KEY);
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
        return Flux.fromIterable(list);*/
return null;
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
