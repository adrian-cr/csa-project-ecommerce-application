package com.cognizant.OrderService.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
   @Id
   @Column(name="order_id")
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
   @Column(name="user_id", nullable=false)
    private Integer userId;
   @Column(name="payment_id", nullable=false)
    private Integer paymentId;
   @Column(name="order_total", nullable=false)
    private Double total;
   @Column(nullable=false)
    private String status;
   @CreationTimestamp
    private String orderDate;
   @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
   @Column(name="order_items")
    private List<Item> orderItems;


    public Order() {
        this.status = "processing";
    }

    public Order(Integer userId, Integer paymentId, Double total, List<Item> orderItems) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.total = total;
        this.status = "processing";
        this.orderItems = orderItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }
}
