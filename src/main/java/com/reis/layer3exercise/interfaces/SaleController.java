package com.reis.layer3exercise.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reis.layer3exercise.domain.SaleService;
import com.reis.layer3exercise.entities.Sale;

@RestController
public class SaleController {
    @Autowired
    private SaleService sales;

    @GetMapping("/home")
    public String home(){
        return "Welcome to the store.";
    }
    //Register a sale
    @PostMapping("/api/products/sale")
    public int makeSale(@RequestBody Sale sale){
        return sales.registerSale(sale);
    }

    //Get all sales
    @GetMapping("/api/sales")
    public List<Sale> getSales(){
        return sales.getSales();
    }

    //Delete ALL sales
    @PostMapping("/api/products/delete-all-sales")
    public void removeAllSales(){
        sales.deleteSales();
    }
}
