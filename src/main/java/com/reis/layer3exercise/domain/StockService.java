package com.reis.layer3exercise.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.reis.layer3exercise.entities.Product;
import com.reis.layer3exercise.entities.Sale;

@Service
public class StockService {
    @Autowired
    private IStockRepository stock;
    @Autowired
    private MongoTemplate mt;

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
    public int addQnt(String prodId, String quantity){
        Optional<Product> aux = getProduct(prodId);
        int qnt = 0;
        if(aux.isPresent()){
            qnt = Integer.parseInt(quantity) + aux.get().getQuantity();
            
            Update update = new Update().set("quantity", qnt);
            mt.updateFirst(new Query(Criteria.where("prodId").is(prodId)), update, Product.class);
        } else {
            throw new IllegalArgumentException("Product: "+ prodId+ " does not exist");
        }
        return qnt;
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
