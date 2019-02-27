package com.geshaofeng.gatewayproxy.config;

import com.geshaofeng.gatewayproxy.ribbon.MyRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/25
 */
@Configuration
public class GatewayConfig {


    @Bean
    public IRule ribbonRule() {
        return new MyRule();
    }
}
