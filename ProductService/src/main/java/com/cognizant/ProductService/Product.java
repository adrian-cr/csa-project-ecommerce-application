package com.cognizant.ProductService;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
   @Id
   @Column(name = "product_id")
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
   @Column(nullable = false, length = 50)
    private String name;
    private String description;
   @Column(nullable = false)
    private Double price;
   @Column(nullable = false)
    private Integer stock;

    public Product() {}

    public Product(String name, String description, Double price, Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
