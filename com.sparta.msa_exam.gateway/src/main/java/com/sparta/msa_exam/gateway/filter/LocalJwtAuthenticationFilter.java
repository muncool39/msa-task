package com.sparta.msa_exam.gateway.filter;

import static com.sparta.msa_exam.gateway.util.JwtValidationType.VALID_TOKEN;

import com.sparta.msa_exam.gateway.util.JwtUtil;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class LocalJwtAuthenticationFilter implements GlobalFilter {

    private final JwtUtil jwtUtil;
    private final static String TOKEN_PREFIX = "Bearer ";

    private final String[] permitPaths = {
            "/auth/sign-up",
            "/auth/sign-in",
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (exist(path)) {
            return chain.filter(exchange);
        }
        final String token = getJwtFromRequest(exchange);
        if (token == null || jwtUtil.getTokenValidate(token)!=VALID_TOKEN) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    private boolean exist(String path){
        return Arrays.asList(permitPaths).contains(path);
    }

    private String getJwtFromRequest(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

}
