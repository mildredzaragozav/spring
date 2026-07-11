package com.belle.springwebflux.service;

import com.belle.springwebflux.dto.OrderDTO;
import com.belle.springwebflux.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOrderService {

    Mono<Order> saveOrder(OrderDTO order);
    Mono<Order> getOrderById(Long id);
    Flux<Order> getAllOrders();
    Flux<Order> getOrdersByProductId(String productId);
    Flux<Order> streamOrderUpdates(); // For real-time updates

}
