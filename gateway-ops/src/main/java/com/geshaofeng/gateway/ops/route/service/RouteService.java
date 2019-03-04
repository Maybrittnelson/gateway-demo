package com.geshaofeng.gateway.ops.route.service;


import com.geshaofeng.gateway.ops.route.repository.entity.RouteEntity;

/**
 * Route
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
public interface RouteService {

    int insertRoute(RouteEntity routeEntity);

    int updateRoute(Long id, RouteEntity routeEntity);

    RouteEntity findRouteById(Long id);
}
