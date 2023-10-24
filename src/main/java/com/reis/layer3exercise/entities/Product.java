package com.reis.layer3exercise.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ProdutoM
 */
@Document(collection="produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    private ObjectId id;
    private String prodId;
    private String description;
    private Integer price;
    private Integer quantity;

    public Product(String prodId, String description, Integer price, Integer quantity) {
        this.prodId = prodId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public void incrementQuantity(int quantity) {
        this.quantity += quantity;
    }
    public ObjectId getId(){
        return this.id;
    }
    public String prodId(){
        return prodId;
    }
    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    @Override
    public String toString() {
        return "Product [prodId=" + prodId + ", description=" + description + ", price=" + price + ", quantity="
                + quantity + "]";
    }

    
}