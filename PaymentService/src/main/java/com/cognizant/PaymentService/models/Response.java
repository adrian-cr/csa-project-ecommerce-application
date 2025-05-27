package com.cognizant.PaymentService.models;

import java.util.Map;

public class Response {
    private Integer userId;
    private Integer paymentId;
    private Double total;
    private Map<Integer, Integer> items;

    public Response() {
    }

    public Response(Integer userId, Integer paymentId, Double total, Map<Integer, Integer> Items) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.total = total;
        this.items = Items;
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

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Response{" +
                "userId=" + userId +
                ", paymentId=" + paymentId +
                ", total=" + total +
                ", items=" + items.toString() +
                '}';
    }
}
