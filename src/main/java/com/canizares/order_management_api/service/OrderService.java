package com.canizares.order_management_api.service;

import com.canizares.order_management_api.exception.ResourceNotFoundException;
import com.canizares.order_management_api.model.Order;
import com.canizares.order_management_api.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }

    public Order save(Order order) {
        return repository.save(order);
    }
}
