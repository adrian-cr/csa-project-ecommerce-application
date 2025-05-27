package com.cognizant.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService ps;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Integer id) {
        try {

            Product product = ps.getProduct(id);
            if (product == null) return ResponseEntity.notFound().build();
            log.info("Product with id " + id + "found");
            return ResponseEntity.ok(product);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
        @PathVariable("id") Integer id,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) Double price,
        @RequestParam(required = false) Integer stock
    ) {
        if (name==null && description==null && price==null && stock==null) return ResponseEntity.badRequest().body("No updatable values found.");
        Product product = ps.getProduct(id);
        if (product == null) return ResponseEntity.status(404).body("Product not found.");
        ps.updateProduct(id, name, description, price, stock);
        return ResponseEntity.ok("Product with id " + id + " updated.");
    }

}
