package com.example.Assignment2Bun.repo;

import com.example.Assignment2Bun.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,Integer> {
    Optional<Order> findByOrderId(int orderId);

}
