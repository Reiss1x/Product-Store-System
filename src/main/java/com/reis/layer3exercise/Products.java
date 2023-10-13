package com.reis.layer3exercise;

import org.bson.types.ObjectId;

public interface Products {
    public void incrementQuantity(int quantity);
    public void decreaseQuantity(int quantity);
    public ObjectId getId();
    public String getDescription();
    public int getPrice();
    public int getQuantity();
}
