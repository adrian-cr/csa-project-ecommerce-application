package com.cognizant.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class ProductService {
    private final ProductRepository productRep;

    @Autowired
    public ProductService(ProductRepository productRep){
        this.productRep = productRep;
        if (productRep.count() == 0) {
            Product p1 = new Product("Samsung Galaxy S20 Ultra", "The Samsung Galaxy S20 Ultra is the latest flagship smartphone, and it's the first smart phone to feature a 12.2-inch OLED display. The device comes with a 6.4-inch front camera and a 12-megapixel selfie camera.", 399.99, 25);
            Product p2 = new Product("Samsung Galaxy S21 Ultra", "The Samsung Galaxy S21 Ultra is the latest flagship smartphone, and it's the first smart phone to feature a 12.2-inch OLED display. The device comes with a 6.4-inch front camera and a 12-megapixel selfie camera.", 499.99, 12);
            Product p3 = new Product("Samsung Galaxy S22 Ultra", "The Samsung Galaxy S22 Ultra is the latest flagship smartphone, and it's the first smart phone to feature a 12.2-inch OLED display. The device comes with a 6.4-inch front camera and a 12-megapixel selfie camera.", 599.99, 667);
            Product p4 = new Product("Samsung Galaxy S23 Ultra", "The Samsung Galaxy S23 Ultra is the latest flagship smartphone, and it's the first smart phone to feature a 12.2-inch OLED display. The device comes with a 6.4-inch front camera and a 12-megapixel selfie camera.", 699.99, 99);
            Product p5 = new Product("Samsung Galaxy S24 Ultra", "The Samsung Galaxy S24 Ultra is the latest flagship smartphone, and it's the first smart phone to feature a 12.2-inch OLED display. The device comes with a 6.4-inch front camera and a 12-megapixel selfie camera.", 799.99, 131);
            productRep.saveAll(asList(p1, p2, p3, p4, p5));
        }
    }

    public void addProduct(Product product){
        productRep.save(product);
    }

    public List<Product> getAllProducts(){
        return productRep.findAll();
    }

    public Product getProduct(Integer id){
        return productRep.findById(id).get();
    }

    public void updateProduct(Integer id, String name, String description, Double price, Integer stock){
        Product product = productRep.findById(id).get();
        if (name != null) product.setName(name);
        if (description != null) product.setDescription(description);
        if (price != null) product.setPrice(price);
        if (stock != null) product.setStock(stock);
        productRep.save(product);
    }

    public void deleteProduct(Integer id){
        productRep.deleteById(id);
    }

}
