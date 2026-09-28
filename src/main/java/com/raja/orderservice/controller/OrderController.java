package com.raja.orderservice.controller;

import com.raja.orderservice.model.Order;
import com.raja.orderservice.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
