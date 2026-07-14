package com.belle.springwebflux.service;

import com.belle.springwebflux.dto.OrderDTO;
import com.belle.springwebflux.model.Order;
import com.belle.springwebflux.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler{
    private final OrderRepository orderRepository;

    /*public OrderHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }*/

    /**
     * getAllOrders is a handler function that returns all Orders objects found in the repository as JSON
     * @return
     */
    @Override
    public Mono<ServerResponse> getAllOrders(ServerRequest request) {
        Flux<Order> orders = orderRepository.findAll();
        return ok().contentType(MediaType.APPLICATION_JSON).body(orders, Order.class);
    }


    /**
     * getOrderById is a handler function that returns a single order, identified by the id path variable.
     * We retrieve that Order from the repository and create a JSON response, if it is found. If it is not found,
     * we use switchIfEmpty(Mono<T>) to return a 404 Not Found response.
     * @param request
     * @return
     */
    @Override
    public Mono<ServerResponse> getOrderById(ServerRequest request) {
        Long orderId = Long.valueOf(request.pathVariable("id"));

        return orderRepository.findById(orderId)
                .flatMap(order -> ok().contentType(MediaType.APPLICATION_JSON).bodyValue(order))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * getOrdersByProductId is a handler function that returns a list of orders that contains a product with a specified productId.
     * @param request
     * @return
     */
    @Override
    public Mono<ServerResponse> getOrdersByProductId(ServerRequest request) {
        String productId = request.pathVariable("productId");
        Flux<Order> orders = orderRepository.findByProductId(productId);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(orders, Order.class);
    }

    /**
     * createOrder is a handler function that stores a new order contained in the request body.
     * @param request
     * @return
     */
    @Override
    public Mono<ServerResponse> createOrder(ServerRequest request) {
        return request.bodyToMono(OrderDTO.class)
                .map(dto -> Order.builder()
                        .productId(dto.productId())
                        .quantity(dto.quantity())
                        .status("PENDING")
                        .build())
                .flatMap(orderRepository::save)
                .flatMap(saved -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .bodyValue(saved));
    }


    /**
     * Note that orderRepository.save(Order) returns Mono<Void>: an empty Mono that emits a completion signal the order
     * has been read from the request and stored. So we use the build(Publisher<Void>) method to send a response when
     * that completion signal is received (that is, when the Order has been saved)
     * @param request
     * @return
     */
    private Mono<ServerResponse> createPersonWithoutDefaultValues(ServerRequest request) {
       /* Mono<Order> order = request.bodyToMono(Order.class);
        return ok().build(orderRepository.saveOrder(order));    // Mono<Void> saveOrder(Mono<Order> order); */
        return null;
    }
}
