package com.canizares.order_management_api.controller;

import com.canizares.order_management_api.model.Order;
import com.canizares.order_management_api.model.dto.OrderRequestDTO;
import com.canizares.order_management_api.model.dto.OrderResponseDTO;
import com.canizares.order_management_api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable("id") long id) {
        OrderResponseDTO response = orderService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderDTO) {
        OrderResponseDTO response = orderService.save(orderDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
