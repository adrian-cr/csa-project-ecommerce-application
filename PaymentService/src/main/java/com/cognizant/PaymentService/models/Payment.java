package com.cognizant.PaymentService.models;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
   @Id
   @Column(name = "payment_id")
   @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Integer id;
   @Column(name="user_id", nullable=false, length=50)
    private Integer userId;
   @Column(nullable=false)
    private Double amount;
   @Column(nullable=false)
    private String currency;
   @Column(nullable=false)
    private String status;
   @Column(nullable=false)
    private String paymentMethod;
    private String provider;


    public Payment() {
        status = "pending";
    }

    public Payment(Integer userId, Double amount, String currency, String paymentMethod, String provider) {
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.status = "pending";
        this.paymentMethod = paymentMethod;
        this.provider = provider;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
