package com.reis.layer3exercise;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    private ObjectId id;
    private String saleId;
    private String clientName;
    private int finalPrice;
    private boolean closed;
    private List<Item> items;
    
    public Sale(String clientName, List<Item> items) {
        this.clientName = clientName;
        this.closed = false;
        this.items = items;
    }

    public ObjectId getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public int getFinalPrice() {
        return finalPrice;
    }
    public String getSaleId(){
        return saleId;
    }

    public boolean isClosed() {
        return closed;
    }
    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Sale [clientName=" + clientName + ", items=" + items + "]";
    }

    
}
