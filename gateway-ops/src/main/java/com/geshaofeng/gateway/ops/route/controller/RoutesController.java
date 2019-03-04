package com.geshaofeng.gateway.ops.route.controller;

import com.geshaofeng.gateway.ops.route.repository.entity.RouteEntity;
import com.geshaofeng.gateway.ops.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * routes
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
@RestController
public class RoutesController {

    @Autowired
    private RouteService routeService;

    @PostMapping("/routes")
    public ResponseEntity<Map> create(@RequestBody RouteEntity route) {
        int i = routeService.insertRoute(route);
        if (i == 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(buildRetBody(403, "routeId：" + route.getRouteId() + "已存在", null));
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id", i);
        return ResponseEntity.created(URI.create("/routes" + i)).body(buildRetBody(0, "success", map));
    }

    @PutMapping("/routes/{id}")
    public ResponseEntity<Map> update(@PathVariable Long id, @RequestBody RouteEntity route) {
        int i = routeService.updateRoute(id, route);
        if (i == 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(buildRetBody(403, "routeId：" + route.getRouteId() + "已存在", null));
        }
        return ResponseEntity.ok(buildRetBody(0, "success", null));
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<RouteEntity> select(@PathVariable Long id) {
        RouteEntity route = routeService.findRouteById(id);
        if (route != null) {
            return ResponseEntity.ok(route);
        }
        return ResponseEntity.notFound().build();
    }


    private Map<String, Object> buildRetBody(int code, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", msg);
        map.put("data", data);
        return map;
    }


}
