package com.cognizant.PaymentService.models;

import java.util.Map;

public class Request {
    private Payment paymentDetails;
    private Map<Integer, Integer> items;

    public Request() {
    }

    public Request(Payment paymentDetails, Map<Integer, Integer> items) {
        this.paymentDetails = paymentDetails;
        this.items = items;
    }

    public Payment getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Payment paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }
}
