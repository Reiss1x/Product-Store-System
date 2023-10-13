package com.reis.layer3exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Classe de serviço, responsável por conter a lógica de negócio e coordenar as operações entre as ckasses de banco de dados
//e as classes de controle.
@Service
public class StoreService {
    // LojaRepositoryImpl estoque;
    @Autowired
    private IStoreRepository stock;

    //retorna o estoque para a classe controle
    public List<ProductM> getStock(){
        List<ProductM> aux = stock.findAll();
        return aux.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
    }
    //retorna o catalogo para a classe controle
    public List<ProductM> getCatalog(){
        return stock.findAll();       
    }

    public Optional<ProductM> getProduct(String prodId){
        return stock.findProductByProdId(prodId);
    }

    //recebe a requisição da classe controle e manda para a de banco de dados
    public void registerProd(ProductM product){
        stock.insert(product);
    }
    public void removeProd(String prodId){
        
        Optional<ProductM> aux = stock.findProductByProdId(prodId);
        if(aux.isPresent()){
            ProductM prod = aux.get();
            stock.deleteById(prod.getId());
        } else {
            throw new IllegalArgumentException("Product does not exist");
        }
    }

    // //envia uma venda ao banco de dados, faz o cálculo do preço com o desconto acima de 10 unidades e retorna o preço
    // public String venda(int id, int quantidade) {
    //     int preco = estoque.venderProd(id, quantidade);
    //     if(preco != -1){
    //         if(quantidade > 10){
    //             preco -= preco*0.10;
    //             preco += preco*0.05;
    //         } 
    //     }
    //     return Integer.toString(preco);
    // }
}
