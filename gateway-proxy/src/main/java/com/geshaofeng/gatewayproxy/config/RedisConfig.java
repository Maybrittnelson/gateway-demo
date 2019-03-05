package com.geshaofeng.gatewayproxy.config;

import com.geshaofeng.gatewayproxy.routes.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

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
        }*/


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }


    @Bean
    public RouteService routeService() {
        return new RouteService();
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(routeService());
    }


    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }

}
