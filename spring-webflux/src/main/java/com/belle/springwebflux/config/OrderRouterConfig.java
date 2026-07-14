package com.belle.springwebflux.config;

import com.belle.springwebflux.service.IOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@RequiredArgsConstructor
public class OrderRouterConfig implements WebFluxConfigurer {

    private final IOrderHandler orderHandler;

    @Bean
    public RouterFunction<ServerResponse> routerConfig() {
        return route()
                .path("/orders", b1 -> b1
                    .nest(accept(MediaType.APPLICATION_JSON), b2 -> b2
                        .GET("/products/{productId}", orderHandler::getOrdersByProductId)
                        .GET("/{id}", orderHandler::getOrderById)
                        .GET(orderHandler::getAllOrders)
                        .POST(orderHandler::createOrder)))
                .add(getDefaultRoute())
                .build();
    }

    private RouterFunction<ServerResponse> getDefaultRoute() {
        return RouterFunctions.route()
                .GET("/hello", accept(MediaType.TEXT_PLAIN), request -> ServerResponse.ok().bodyValue("Hello world!"))
                .GET("/hola", accept(MediaType.TEXT_PLAIN), request -> ServerResponse.ok().bodyValue("¡Hola mundo!"))
                .build();
    }
}
