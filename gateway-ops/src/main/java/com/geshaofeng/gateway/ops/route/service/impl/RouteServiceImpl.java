package com.geshaofeng.gateway.ops.route.service.impl;

import com.alibaba.fastjson.JSON;
import com.geshaofeng.gateway.ops.route.repository.RouteRepository;
import com.geshaofeng.gateway.ops.route.repository.entity.RouteEntity;
import com.geshaofeng.gateway.ops.route.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
@Service
public class RouteServiceImpl implements RouteService {

    Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    public static final String KEY = "route";

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ChannelTopic topic;

    @Override
    public int insertRoute(RouteEntity route) {
        int i = routeRepository.insertRoute(route);
        if (i != 0) {
            stringRedisTemplate.opsForHash().put(KEY, route.getRouteId(), JSON.toJSONString(route));
            publish("create route");
        }
        return i;
    }

    @Override
    public int updateRoute(Long id, RouteEntity route) {
        int i = routeRepository.updateRoute(id, route);
        if (i != 0) {
            if (route.getState() != 0) {
                stringRedisTemplate.opsForHash().delete(KEY, route.getRouteId());
            } else {
                stringRedisTemplate.opsForHash().put(KEY, route.getRouteId(), JSON.toJSONString(route));
            }
            publish("update route");
        }
        return i;
    }

    @Override
    public RouteEntity findRouteById(Long id) {
        return routeRepository.findRouteById(id);
    }

    @Override
    public void publish(String message) {
        stringRedisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
