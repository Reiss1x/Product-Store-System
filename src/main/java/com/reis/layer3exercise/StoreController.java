package com.reis.layer3exercise;

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



//Classe controller responsável pelas solicitações dos clientes e retornar respostas apropriadas.
@RestController
public class StoreController {
    @Autowired
    private SaleService sales;
    @Autowired
    private StockService stock;

    @GetMapping("/home")
    public String preco(){
        return "Welcome to the store.";
    }
    
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
    
    //Register a sale
    @PostMapping("/api/products/sale")
    public int makeSale(@RequestBody Sale sale){
        return sales.registerSale(sale, stock.getCatalog());
    }

    //Get all sales
    @GetMapping("/api/sales")
    public List<Sale> getSales(){
        return sales.getSales();
    }
    
}
