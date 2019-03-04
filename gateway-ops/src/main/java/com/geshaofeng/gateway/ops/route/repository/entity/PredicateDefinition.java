package com.geshaofeng.gateway.ops.route.repository.entity;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 断言
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
@Validated
public class PredicateDefinition {
    @NotNull
    private String name;
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "PredicateDefinition{" +
                "name='" + name + '\'' +
                ", args=" + args +
                '}';
    }
}
