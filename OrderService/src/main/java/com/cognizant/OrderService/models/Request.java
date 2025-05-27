package com.cognizant.OrderService.models;

import java.util.Map;

public class Request {
    private Integer userId;
    private Integer paymentId;
    private Double total;
    private Map<Integer, Integer> items;

    public Request() {

    }

    public Request(Integer userId, Integer paymentId, Double total, Map<Integer, Integer> items) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.total = total;
        this.items = items;
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
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    public Map<Integer, Integer> getItems() {
        return items;
    }
    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Request{" +
                "customerId='" + userId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", items=" + items +
                '}';
    }
}
