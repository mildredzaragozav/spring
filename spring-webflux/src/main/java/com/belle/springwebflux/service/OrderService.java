package com.belle.springwebflux.service;

import com.belle.springwebflux.dto.OrderDTO;
import com.belle.springwebflux.model.Order;
import com.belle.springwebflux.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final Random random = new Random();

    @Override
    public Mono<Order> saveOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                        .productId(orderDTO.productId())
                        .quantity(orderDTO.quantity())
                        .status("PENDING")  //Default status for new orders
                        .build();
        return orderRepository.save(order);
    }

    @Override
    public Mono<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Flux<Order> getOrdersByProductId(String productId) {
        return orderRepository.findByProductId(productId);
    }

    @Override
    public Flux<Order> streamOrderUpdates() {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(ticket -> {
                    Order order = Order.builder()
                            .id(Math.abs(random.nextLong() + 201))
                            .productId("Product-" + ticket % 3 + 1) //Cycle through 3 product IDs
                            .quantity((int)(Math.random() * 5) + 1) //Random quantity 1-5
                            .status(getRandomStatus())   //Get a random status
                            .build();
                    return Mono.just(order);
                })
                .take(10) // Closes connection automatically after 10 elements
                .log(); //log updates for demonstration
    }

    private String getRandomStatus() {
        String[] status = {"PENDING", "PROCESSING", "SHIPPED", "DELIVERED"};
        int randomIndex = (int)(Math.random() * status.length);
        return status[randomIndex];
    }
}
