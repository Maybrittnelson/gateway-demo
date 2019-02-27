package com.geshaofeng.gatewayproxy.filter;

import org.bouncycastle.util.Strings;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author <a href="mailto:ge.sf.chn@gmail.com">shaofeng</a>
 * @see
 * @since 2019/2/25
 */
public abstract class AbstractGlobalFilter implements GlobalFilter {


    protected static final String CACHE_REQUEST_BODY_OBJECT_KEY = "cachedRequestBodyObject";

    /**
     * 如果content-type为空，就不缓存body页面的数据
     * @param exchange
     * @return
     * @see CacheBodyGatewayFilter
     */
    public static String toRaw(ServerWebExchange exchange) {
        Flux<DataBuffer> body = exchange.getAttribute(CACHE_REQUEST_BODY_OBJECT_KEY);
        //Flux<DataBuffer> body = exchange.getRequest().getBody();
        AtomicReference<String> rawRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            DataBufferUtils.release(buffer);
            rawRef.set(Strings.fromUTF8ByteArray(bytes));
        });
        return rawRef.get();
    }
}
