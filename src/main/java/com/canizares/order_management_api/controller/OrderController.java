package com.canizares.order_management_api.controller;

import com.canizares.order_management_api.model.Order;
import com.canizares.order_management_api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") long id) {
        return orderService.findById(id);
    }
    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        System.out.println();
        return orderService.save(order);
    }
}
