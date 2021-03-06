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
public class IpFilter implements GlobalFilter, Ordered {



    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("执行第一个过滤器IpFilter--Start");
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        String hostName = remoteAddress.getHostName();
        log.info("过滤器IpFilter的ip:"+hostName);

        return chain.filter(exchange);
    }
}
