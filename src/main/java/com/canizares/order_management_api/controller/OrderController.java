package com.canizares.order_management_api.controller;

import com.canizares.order_management_api.model.Order;
import com.canizares.order_management_api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        Order updatedOrder = orderService.update(id, newStatus);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
