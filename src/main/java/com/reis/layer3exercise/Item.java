package com.reis.layer3exercise;

public class Item {
    private String prodId;
    private int quantity;
    public Item(String prodId, int quantity) {
        this.prodId = prodId;
        this.quantity = quantity;
    }
    public String getProdId() {
        return prodId;
    }
    public int getQuantity() {
        return quantity;
    }
    @Override
    public String toString() {
        return "Item [prodId=" + prodId + ", quantity=" + quantity + "]";
    }
    

    
}
