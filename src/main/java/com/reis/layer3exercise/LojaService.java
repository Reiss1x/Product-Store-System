package com.reis.layer3exercise;

import org.springframework.stereotype.Service;

@Service
public class LojaService {
    LojaRepositoryImpl estoque;
    

    public LojaService(LojaRepositoryImpl estoque){
        this.estoque = estoque;
    }

    public Iterable<Produto> getAllProdutos(){
        return estoque.getCatalogo();
    }

    public void cadastraProduto(Produto produto){
        estoque.setProduto(produto);
    }

    public void venda(int id, int quantidade) {
        int preco = estoque.venderProd(id, quantidade);
        if(preco != -1){
            if(quantidade > 10){
                preco -= preco*0.10;
                preco += preco*0.05;
            } 
        }     
    }
}
