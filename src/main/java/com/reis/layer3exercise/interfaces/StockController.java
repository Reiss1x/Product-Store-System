package com.reis.layer3exercise.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reis.layer3exercise.domain.StockService;
import com.reis.layer3exercise.entities.Product;
@RestController
public class StockController {
    
    @Autowired
    private StockService stock;

    //Register products
    @PostMapping("/api/products/new-product")
    public void registerNewProduct(@RequestBody List<Product> products){
        for(Product p : products){
        stock.registerProd(p);
        }
    }

    //Get available products
    @GetMapping("/stock")
    public ResponseEntity<List<Product>> stock(){
        return new ResponseEntity<List<Product>>(stock.getStock(),HttpStatus.OK);
    }

    //Get all products
    @RequestMapping("/api/products")
    public ResponseEntity<List<Product>> catalog(){
        return new ResponseEntity<List<Product>>(stock.getCatalog(),HttpStatus.OK);
    }
    
    //Get specific products (byId)
    @GetMapping("/api/products/{prodId}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable String prodId){
        return new ResponseEntity<Optional<Product>>(stock.getProduct(prodId), HttpStatus.OK);
    }

    //Add quantity of product to stock (byId)
    @PostMapping("/api/products/{prodId}/addToStock/{quantity}")
    public int addQnt(@PathVariable String prodId, @PathVariable String quantity){
        return stock.addQnt(prodId, quantity);
        
    }
    
    //Delete specific product (byId)
    @PostMapping("/api/products/delete-product")
    public void removeProduct(String prodId){
        stock.removeProd(prodId);
    }
    
    //Delete ALL products
    @PostMapping("/api/products/delete-all-products")
    public void removeAllProducts(){
        stock.removeAllProducts();
    }

    
}
