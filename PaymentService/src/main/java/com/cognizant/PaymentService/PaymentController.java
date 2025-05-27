package com.cognizant.PaymentService;

import com.cognizant.PaymentService.models.Payment;
import com.cognizant.PaymentService.models.Request;
import com.cognizant.PaymentService.models.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired private PaymentService ps;
    @Autowired private RestTemplate restTemp;
    @CircuitBreaker(name="paymentService", fallbackMethod="fallbackAddPayment")
    @PostMapping
    public ResponseEntity<?> addPayment(
        @RequestBody Request request,
        @RequestHeader("Authorization") String auth
    ) {
        //Register transaction:
        Payment transaction = request.getPaymentDetails();
        if (transaction == null) return ResponseEntity.badRequest().build();
        ps.addPayment(transaction);

        //Verify authorization:
        try {
            if (auth == null) {
                ps.updatePaymentStatus(transaction.getId(), "declined");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", auth);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ResponseEntity<?> authResponse = restTemp.exchange("http://localhost:8084/auth", HttpMethod.POST, entity, ResponseEntity.class);
            if (authResponse.getStatusCode() != HttpStatus.OK) {
                ps.updatePaymentStatus(transaction.getId(), "declined");
                return authResponse;
            }
        } catch (Exception e) {
            ps.updatePaymentStatus(transaction.getId(), "declined");
            return ResponseEntity.badRequest().body("Invalid request.");
        }

        //Approve payment:
        ps.updatePaymentStatus(transaction.getId(), "approved");

        //Forward data to Order:
        Response response = new Response(transaction.getUserId(), transaction.getId(), transaction.getAmount(), request.getItems());
        ResponseEntity<?> orderResponse = restTemp.postForEntity("http://localhost:8083/orders", response, String.class);

        //Check for order-related issues:
        if (orderResponse.getStatusCode() != HttpStatus.OK) {
            ps.updatePaymentStatus(transaction.getId(), "cancelled");
            return orderResponse;
        }

        //Accept payment:
        ps.updatePaymentStatus(transaction.getId(), "accepted");
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<?> fallbackAddPayment(
        @RequestBody Request request,
        @RequestHeader("Authorization") String auth
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service is unavailable. Please try again later");
    }

}
