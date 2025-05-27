package com.cognizant.PaymentService;

import com.cognizant.PaymentService.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class PaymentService {
    private final PaymentRepository paymentRep;

    @Autowired
    public PaymentService(PaymentRepository paymentRep) {
        this.paymentRep = paymentRep;
        if (paymentRep.count() == 0) {
            Payment p1 = new Payment(4, 67.89, "USD", "credit card", "Visa");
            Payment p2 = new Payment(1, 12.34, "MXN", "credit card", "Mastercard");
            Payment p3 = new Payment(3, 45.00, "UAH", "cash", null);
            Payment p4 = new Payment(2, 40.25, "EUR", "debit card", "Visa");
            Payment p5 = new Payment(5, 276.01, "GBP", "cash", null);
            paymentRep.saveAll(asList(p1, p2, p3, p4, p5));
        }
    }

    public void addPayment(Payment payment) {
        paymentRep.save(payment);
    }

    public void updatePaymentStatus(Integer id, String status) {
        Payment payment = paymentRep.findById(id).get();
        if (status != null) payment.setStatus(status);
        paymentRep.save(payment);
    }
}