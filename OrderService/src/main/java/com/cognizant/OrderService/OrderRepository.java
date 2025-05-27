package com.cognizant.OrderService;

import com.cognizant.OrderService.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
