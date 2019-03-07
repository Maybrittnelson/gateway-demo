package com.geshaofeng.gatewayproxy.config;

import com.geshaofeng.gatewayproxy.routes.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Bean
    public RouteService routeService() {
        return new RouteService();
    }

/*    @Bean
    ReactiveRedisMessageListenerContainer container(RouteService routeService, ReactiveRedisConnectionFactory connectionFactory) {
        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(connectionFactory);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            container.destroyLater().subscribe();
        }));
        container.receive(topic())
                .map(p -> p.getMessage())
                .subscribe(message -> {
                    log.info("Received <{}>", message);
                    routeService.publishRefreshEvent();
                });

        return container;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }*/

}
