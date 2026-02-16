package com.canizares.order_management_api.controller;

import com.canizares.order_management_api.model.Order;
import com.canizares.order_management_api.repository.IOrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private IOrderRepository orderRepository;

    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        System.out.println();
        return orderRepository.save(order);
    }
}
