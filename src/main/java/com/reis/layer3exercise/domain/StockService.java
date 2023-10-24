package com.reis.layer3exercise.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reis.layer3exercise.entities.Product;

@Service
public class StockService {
    @Autowired
    private IStockRepository stock;

    //retorna o estoque para a classe controle
    public List<Product> getStock(){
        List<Product> aux = stock.findAll();
        return aux.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
    }
    //retorna o catalogo para a classe controle
    public List<Product> getCatalog(){
        return stock.findAll();       
    }

    public Optional<Product> getProduct(String prodId){
        return stock.findProductByProdId(prodId);
    }

    //recebe a requisição da classe controle e manda para a de banco de dados
    public void registerProd(Product product){
        stock.insert(product);
    }
    public void removeProd(String prodId){
        
        Optional<Product> aux = stock.findProductByProdId(prodId);
        if(aux.isPresent()){
            Product prod = aux.get();
            stock.deleteById(prod.getId());
        } else {
            throw new IllegalArgumentException("Product does not exist");
        }
    }
    public void removeAllProducts(){
        stock.deleteAll();
    }
}
