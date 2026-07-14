package com.belle.springwebflux.service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IOrderHandler {
    //ServerRequest and ServerResponse are immutable interfaces that offer convenient access to the HTTP request and response.

    Mono<ServerResponse> createOrder(ServerRequest request);
    Mono<ServerResponse> getOrderById(ServerRequest request);
    Mono<ServerResponse> getAllOrders(ServerRequest request);
    Mono<ServerResponse> getOrdersByProductId(ServerRequest request);
}
