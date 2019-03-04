package com.geshaofeng.gateway.ops.route.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.geshaofeng.gateway.ops.route.repository.entity.FilterDefinition;
import com.geshaofeng.gateway.ops.route.repository.entity.PredicateDefinition;
import com.geshaofeng.gateway.ops.route.repository.entity.RouteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
@Repository
public class RouteRepository {

    private Logger logger = LoggerFactory.getLogger(RouteRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<RouteEntity> rowMapper = (resultSet, i) -> {
        RouteEntity route = new RouteEntity();
        route.setId(resultSet.getLong("id"));
        route.setUri(resultSet.getString("uri"));
        route.setOrder(resultSet.getInt("order"));
        route.setStatus(resultSet.getInt("status"));
        route.setRouteId(resultSet.getString("route_id"));
        List<PredicateDefinition> predicates = JSONObject.parseArray(resultSet.getString("predicates"), PredicateDefinition.class);
        route.setPredicates(predicates);
        List<FilterDefinition> filters = JSONObject.parseArray(resultSet.getString("filters"), FilterDefinition.class);
        route.setFilters(filters);
        return route;
    };

    public int insertRoute(RouteEntity route) {
        int update = 0;
        String sql = "insert into route(route_id, uri, `order`, predicates, filters)" +
                "values(?, ?, ? , ?, ?)";
        ArrayList<Object> args = new ArrayList<>();
        args.add(route.getRouteId());
        args.add(route.getUri());
        args.add(route.getOrder());
        args.add(JSON.toJSON(route.getPredicates()).toString());
        args.add(JSON.toJSON(route.getFilters()).toString());
        try {
            update = jdbcTemplate.update(sql, args.toArray());
        } catch (DuplicateKeyException ex) {
            logger.error("route_id:{}重复", route.getRouteId());
        }
        return update;
    }

    public int updateRoute(Long id, RouteEntity route) {
        int update = 0;
        String sql = "update route set route_id=?, uri=?, `order`=?, predicates=?, filters=?" +
                "where id=?";
        List<Object> args = new ArrayList<>();
        args.add(route.getRouteId());
        args.add(route.getUri());
        args.add(route.getOrder());
        args.add(JSON.toJSON(route.getPredicates()).toString());
        args.add(JSON.toJSON(route.getFilters()).toString());
        args.add(id);
        try {
            update = jdbcTemplate.update(sql, args.toArray());
        } catch (DuplicateKeyException ex) {
            logger.error("route_id:{}重复", route.getRouteId());
        }
        return update;
    }

    public RouteEntity findRouteById(Long id) {
        String sql = "select * from route where id=? ";
        RouteEntity route = jdbcTemplate.queryForObject(sql, new Object[]{id}, this.rowMapper);
        return route;
    }

}
