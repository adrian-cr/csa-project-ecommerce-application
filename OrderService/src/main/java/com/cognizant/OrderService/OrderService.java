package com.cognizant.OrderService;

import com.cognizant.OrderService.models.Order;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRep;
    @CircuitBreaker(name="paymentService", fallbackMethod="fallbackAddPayment")
    public Order addOrder(Order order) {
        orderRep.save(order);
        return order;
    }
}