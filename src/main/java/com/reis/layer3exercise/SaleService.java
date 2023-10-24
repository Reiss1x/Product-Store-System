package com.reis.layer3exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    @Autowired
    private ISaleRepository sr;
    @Autowired
    private IStockRepository stockRepository;
    @Autowired
    private MongoTemplate mt;

    public int registerSale(Sale sale, List<Product> list){
        sr.insert(sale);
        return closeSale(sale);
    }

    public int closeSale(Sale sale){
        List<Product> stock = stockRepository.findAll();
        int finalPrice = 0;
        for(Item i : sale.getItems()){
            if(!(stock.get(Integer.parseInt(i.getProdId())-1).getQuantity() >= i.getQuantity())){
                throw new IllegalArgumentException("Product: "+ i.getProdId() + " out of stock.");
            }
        }
        
        for(Item item : sale.getItems()){

            Product prod = stock.get(Integer.parseInt(item.getProdId())-1);
            finalPrice += prod.getPrice() * item.getQuantity();
            int qnt = prod.getQuantity() - item.getQuantity();   
            Update prodUpdate = new Update().set("quantity", qnt);
            mt.updateFirst(new Query(Criteria.where("prodId").is(prod.getProdId())), prodUpdate, Product.class);
        }
        updateSaleValues(finalPrice, sale.getSaleId());
        return finalPrice;
    }
    
    public void updateSaleValues(int saleFinalPrice, String saleId){
        Update update1 = new Update().set("finalPrice", saleFinalPrice);
        Update update2 = new Update().set("closed", true);
        mt.updateFirst(new Query(Criteria.where("saleId").is(saleId)), update1, Sale.class);
        mt.updateFirst(new Query(Criteria.where("saleId").is(saleId)), update2, Sale.class);
    }

    public List<Sale> getSales() {
        return sr.findAll();
    }
    public void deleteSales(){
        sr.deleteAll();
    }
}
