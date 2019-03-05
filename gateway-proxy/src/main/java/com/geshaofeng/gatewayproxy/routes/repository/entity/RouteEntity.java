package com.geshaofeng.gatewayproxy.routes.repository.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * route
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
@Validated
public class RouteEntity implements Serializable {
    private static final long serialVersionUID = 790549745538222048L;
    private Long id;
    @JsonAlias("route_id")
    private String routeId;
    @NotNull
    private String uri;
    private int order;
    @NotEmpty
    @Valid
    private List<PredicateDefinition> predicates;
    @Valid
    private List<FilterDefinition> filters;
    private int state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<PredicateDefinition> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<PredicateDefinition> predicates) {
        this.predicates = predicates;
    }

    public List<FilterDefinition> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterDefinition> filters) {
        this.filters = filters;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "id=" + id +
                ", routeId='" + routeId + '\'' +
                ", uri='" + uri + '\'' +
                ", order=" + order +
                ", predicates=" + predicates +
                ", filters=" + filters +
                ", state=" + state +
                '}';
    }
}
