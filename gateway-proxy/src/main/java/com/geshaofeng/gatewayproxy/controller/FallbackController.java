package com.geshaofeng.gatewayproxy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/25
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public HttpStatus fallback() {
        return HttpStatus.GATEWAY_TIMEOUT;
    }
}
