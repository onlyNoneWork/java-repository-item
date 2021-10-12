package com.changgou.gateway.filter;

import com.changgou.util.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: hui.jin
 * @date: 2021/10/12 17:19
 */
@Slf4j
public class AuthorzedFilter implements GlobalFilter, Ordered {

    private static final String AUTHORZED_TOKEN = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //如果是登录则直接放行
        if (request.getURI().getPath().contains("/login")) {
            return chain.filter(exchange);
        }
        //获取令牌
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORZED_TOKEN);
        //判断是否有令牌,如果没有则没有权限访问
        if (!StringUtils.hasLength(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //解析令牌
        try{
            JWTUtil.parseJWT(token);
        } catch (Exception e) {
            //解析失败,则token有问题
            log.error("解析token失败:"+e);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
