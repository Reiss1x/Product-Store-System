package com.reis.layer3exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;


//Classe de serviço, responsável por conter a lógica de negócio e coordenar as operações entre as ckasses de banco de dados
//e as classes de controle.
@Service
public class StoreService {
    // LojaRepositoryImpl estoque;
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
    public void removeProd(String prodId){
        
        Optional<Product> aux = stock.findProductByProdId(prodId);
        if(aux.isPresent()){
            Product prod = aux.get();
            stock.deleteById(prod.getId());
        } else {
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    //envia uma venda ao banco de dados, faz o cálculo do preço com o desconto acima de 10 unidades e retorna o preço
    public String sellProd(String prodId, int quantity){

        Optional<Product> aux = stock.findProductByProdId(prodId);
        if(aux.isPresent()){
            Product prod = aux.get();
            if(prod.getQuantity() - quantity >= 0){

                int newqnt = prod.getQuantity() - quantity;

                mt.update(Product.class)
                    .matching(Criteria.where("prodId").is(prodId))
                    .apply(new Update().set("quantity", newqnt))
                    .first();

                return Integer.toString(prod.getPrice() * quantity);
            }
            else {
                throw new IllegalArgumentException("Product out of stock");
            }
            
        } else {
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    //envia uma venda ao banco de dados, faz o cálculo do preço com o desconto acima de 10 unidades e retorna o preço
}
