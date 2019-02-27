package com.geshaofeng.gatewayproxy.user;

import com.geshaofeng.gatewayproxy.user.bean.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html#spring-cloud-feign-hystrix-fallback
 * You also need to declare your implementation as a Spring bean.
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/26
 */
@Component
public class UserServiceFallback implements UserService {
    @Override
    public boolean saveUser(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
