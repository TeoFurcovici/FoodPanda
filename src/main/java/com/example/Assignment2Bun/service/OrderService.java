package com.example.Assignment2Bun.service;

import com.example.Assignment2Bun.model.Order;
import com.example.Assignment2Bun.model.OrderStatus;
import com.example.Assignment2Bun.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    public  void save(Order n) {
        orderRepository.save(n);
    }
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Order order,String orderStatus)
    {
        OrderStatus orderStatus1= OrderStatus.valueOf(orderStatus);
        order.setOrderStatus(orderStatus1);
        orderRepository.save(order);
        return  order;
    }
    public Optional<Order> findByOrderId(int orderId)
    {
       return orderRepository.findByOrderId(orderId);
    }

}
