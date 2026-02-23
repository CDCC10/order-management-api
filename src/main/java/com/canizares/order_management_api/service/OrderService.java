package com.canizares.order_management_api.service;

import com.canizares.order_management_api.exception.ResourceNotFoundException;
import com.canizares.order_management_api.model.Order;
import com.canizares.order_management_api.model.dto.OrderRequestDTO;
import com.canizares.order_management_api.model.dto.OrderResponseDTO;
import com.canizares.order_management_api.repository.IOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public OrderResponseDTO findById(long id) {
        Order order = getOrder(id);
        return modelMapper.map(order, OrderResponseDTO.class);
    }

    public OrderResponseDTO save(OrderRequestDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponseDTO.class);
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
