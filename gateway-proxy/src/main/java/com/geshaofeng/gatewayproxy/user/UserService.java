package com.geshaofeng.gatewayproxy.user;

import com.geshaofeng.gatewayproxy.user.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "${user.service.name}", fallback = UserServiceFallback.class)
public interface UserService {

    @PostMapping("/user/save")
    boolean saveUser(User user);

    @GetMapping("/user/find/all")
    List<User> findAll();
}
