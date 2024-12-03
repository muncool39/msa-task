package com.sparta.msa_exam.gateway.filter;

import java.net.URI;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomPostFilter implements GlobalFilter, Ordered {

    private static final String SERVER_PORT_HEADER = "Server-Port";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            exchange
                    .getResponse()
                    .getHeaders()
                    .add(SERVER_PORT_HEADER, getPort(exchange));
        }));
   }

   private String getPort(ServerWebExchange exchange) {
       URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
       return (uri!=null)
               ? String.valueOf(uri.getPort())
               : null;
   }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
