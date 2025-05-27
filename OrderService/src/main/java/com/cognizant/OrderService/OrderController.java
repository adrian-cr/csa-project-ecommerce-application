package com.cognizant.OrderService;

import com.cognizant.OrderService.models.Item;
import com.cognizant.OrderService.models.Order;
import com.cognizant.OrderService.models.Product;
import com.cognizant.OrderService.models.Request;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired private OrderService ps;
    @Autowired private RestTemplate restTemp;

    @PostMapping
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallbackAddOrder")
    public ResponseEntity<?> addOrder(@RequestBody Request request) {
        Map<Integer, Integer> newStock = new HashMap<>();
        List<Item> items = new ArrayList<>();
        //Check every product is available:
        for (Integer itemId : request.getItems().keySet()) {
            ResponseEntity<?> productResponse = restTemp.getForEntity("http://localhost:8081/products/" + itemId, Product.class);
            if (!productResponse.getStatusCode().is2xxSuccessful()) return ResponseEntity.badRequest().body("Product with id " + itemId + " not found.");
            Product product = (Product) productResponse.getBody();
            if (product == null) return ResponseEntity.badRequest().body("Product with id " + itemId + " not found.");
            Integer productStock = product.getStock();
            Integer quantity = request.getItems().get(itemId);
            if (quantity > 0) {
                if (quantity > productStock) {
                    return ResponseEntity.badRequest().body("Insufficient stock for item " + itemId + ".");
                }
            }
            newStock.put(itemId, productStock - quantity);
        }
        //Update product stock:
        for (Integer itemId : newStock.keySet()) {
            restTemp.put("http://localhost:8081/products/" + itemId + "?stock=" + newStock.get(itemId), Product.class);
        }
        //Add order:
        for (Integer itemId : request.getItems().keySet()) {
            Item item = new Item(
                    itemId,
                    request.getItems().get(itemId)
            );
            items.add(item);
        }
        Order order = new Order(
            request.getUserId(),
            request.getPaymentId(),
            request.getTotal(),
            items
        );
        ps.addOrder(order);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> fallbackAddOrder(Request request, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding order.");
    }

}
