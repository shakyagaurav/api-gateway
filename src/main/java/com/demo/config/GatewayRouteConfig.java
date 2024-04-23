package com.demo.config;

import com.demo.security.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    private final AuthenticationFilter filter;

    public GatewayRouteConfig(AuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authentication-service", r -> r.path("/api/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8083"))
                .route("payment-service", r -> r.path("/api/v1/payment/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8084"))
                .build();
    }

}
