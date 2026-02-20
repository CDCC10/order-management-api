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
    private IOrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(long id) {
        return getOrder(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order update(long id, String newStatus) {
        Order order = getOrder(id);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public void delete(long id) {
        Order order = getOrder(id);
        order.setActive(false);
        orderRepository.save(order);
    }

    private Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
    }
}
