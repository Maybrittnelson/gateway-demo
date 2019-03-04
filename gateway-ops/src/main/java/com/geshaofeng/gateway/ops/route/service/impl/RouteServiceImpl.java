package com.geshaofeng.gateway.ops.route.service.impl;

import com.geshaofeng.gateway.ops.route.repository.RouteRepository;
import com.geshaofeng.gateway.ops.route.repository.entity.RouteEntity;
import com.geshaofeng.gateway.ops.route.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
@Service
public class RouteServiceImpl implements RouteService {

    Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public int insertRoute(RouteEntity route) {
        return routeRepository.insertRoute(route);
    }

    @Override
    public int updateRoute(Long id, RouteEntity route) {
        return routeRepository.updateRoute(id, route);
    }

    @Override
    public RouteEntity findRouteById(Long id) {
        return routeRepository.findRouteById(id);
    }
}
