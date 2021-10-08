package com.changgou.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * @author: hui.jin
 * @date: 2021/9/30 15:03
 */
@Slf4j
public class UrlFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("执行第二个过滤器UrlFilter--Start");
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        log.info("过滤器UrlFilter的url:"+url);

        return chain.filter(exchange);
    }
}
