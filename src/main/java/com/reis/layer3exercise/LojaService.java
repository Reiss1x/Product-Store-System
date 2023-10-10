package com.reis.layer3exercise;

import org.springframework.stereotype.Service;

@Service
public class LojaService {
    // LojaRepositoryImpl estoque;
    LojaJDBCImpl estoque;

    public LojaService(LojaJDBCImpl estoque){
        this.estoque = estoque;

    }

    public Iterable<Produto> getEstoque(){
        return estoque.getEstoque();
    }
    public  Iterable<Produto> getCatalogo(){
        return estoque.getCatalogo();
    }

    public void cadastraProduto(Produto produto){
        estoque.setProduto(produto);
    }

    public int venda(int id, int quantidade) {
        int preco = estoque.venderProd(id, quantidade);
        if(preco != -1){
            if(quantidade > 10){
                preco -= preco*0.10;
                preco += preco*0.05;
            } 
        }
        return preco;
    }
}
