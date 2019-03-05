package com.geshaofeng.gatewayproxy.routes.service;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/3/4
 */
public class RouteService implements ApplicationEventPublisherAware, MessageListener {
    private ApplicationEventPublisher publisher;

    public static List<String> messageList = new ArrayList<String>();

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishRefreshEvent() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
        //重新刷新
        this.publishRefreshEvent();
        System.out.println("Message received: " + new String(message.getBody()));
    }
}
