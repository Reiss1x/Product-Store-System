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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



//Classe controller responsável pelas solicitações dos clientes e retornar respostas apropriadas.
@RestController
public class StoreController {
    @Autowired
    private StoreService store;

    @GetMapping("/home")
    public String preco(){
        return "Welcome to the store.";
    }

    @GetMapping("/stock")
    public ResponseEntity<List<ProductM>> stock(){
        return new ResponseEntity<List<ProductM>>(store.getStock(),HttpStatus.OK);
    }

    @RequestMapping("/api/products")
    public ResponseEntity<List<ProductM>> catalog(){
        return new ResponseEntity<List<ProductM>>(store.getCatalog(),HttpStatus.OK);
    }

    @GetMapping("/api/products/{prodId}")
    public ResponseEntity<Optional<ProductM>> getProduct(@PathVariable String prodId){
        return new ResponseEntity<Optional<ProductM>>(store.getProduct(prodId), HttpStatus.OK);
    }

    @PostMapping("/api/products/new-product")
    public void registerNewProduct(@RequestBody List<ProductM> products){
        for(ProductM p : products){
        store.registerProd(p);
        }
    }
    @PostMapping("/api/products/delete-product")
    public void removeProduct(String prodId){
        store.removeProd(prodId);
    }
    @PostMapping("/stock/venda")
    public String venda(@RequestParam("prodId") String id, @RequestParam("quantity") int quantity){
        String aux = store.sellProd(id,quantity);
        return aux;
    }
}
