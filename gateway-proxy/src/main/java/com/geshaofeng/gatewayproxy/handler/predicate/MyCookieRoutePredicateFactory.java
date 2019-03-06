package com.geshaofeng.gatewayproxy.handler.predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpCookie;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * TODO
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/6
 */
public class MyCookieRoutePredicateFactory extends AbstractRoutePredicateFactory<MyCookieRoutePredicateFactory.Config> {
    private Logger logger = LoggerFactory.getLogger(MyCookieRoutePredicateFactory.class);

    private static final String NAME_KEY = "name";
    private static final String VAR_KEY = "var";
    private static final String CONDITION_KEY = "condition";


    private ScriptEngine javaScript = new ScriptEngineManager().getEngineByName("JavaScript");

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY, VAR_KEY, CONDITION_KEY);
    }

    public MyCookieRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            List<HttpCookie> cookies = exchange.getRequest().getCookies().get(config.getName());
            if (cookies == null) {
                return false;
            }
            for (HttpCookie cookie : cookies) {
                try {
                    boolean flag = (boolean) javaScript.eval(config.condition.replaceAll(config.getVar(), cookie.getValue()));
                    return flag;
                } catch (ScriptException e) {
                    logger.error("Error JavaScript eval Cookie：config:{}, error:{}", config, e);
                }
            }
            return false;
        };
    }

    @Validated
    public static class Config {
        @NotEmpty
        private String name;
        @NotEmpty
        private String condition;
        @NotEmpty
        private String var;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getVar() {
            return var;
        }

        public void setVar(String var) {
            this.var = var;
        }

        @Override
        public String toString() {
            return "Config{" +
                    "name='" + name + '\'' +
                    ", condition='" + condition + '\'' +
                    ", var='" + var + '\'' +
                    '}';
        }
    }
}
