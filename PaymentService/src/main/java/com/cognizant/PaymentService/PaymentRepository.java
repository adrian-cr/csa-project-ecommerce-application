package com.cognizant.PaymentService;

import com.cognizant.PaymentService.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
