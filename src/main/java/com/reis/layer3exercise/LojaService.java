package com.reis.layer3exercise;

import org.springframework.stereotype.Service;


//Classe de serviço, responsável por conter a lógica de negócio e coordenar as operações entre as ckasses de banco de dados
//e as classes de controle.

@Service
public class LojaService {
    // LojaRepositoryImpl estoque;
    LojaJDBCImpl estoque;

    public LojaService(LojaJDBCImpl estoque){
        this.estoque = estoque;

    }

    //retorna o estoque para a classe controle
    public Iterable<Produto> getEstoque(){
        return estoque.getEstoque();
    }
    //retorna o catalogo para a classe controle
    public  Iterable<Produto> getCatalogo(){
        return estoque.getCatalogo();
    }

    //recebe a requisição da classe controle e manda para a de banco de dados
    public void cadastraProduto(Produto produto){
        estoque.setProduto(produto);
    }

    //envia uma venda ao banco de dados, faz o cálculo do preço com o desconto acima de 10 unidades e retorna o preço
    public String venda(int id, int quantidade) {
        int preco = estoque.venderProd(id, quantidade);
        if(preco != -1){
            if(quantidade > 10){
                preco -= preco*0.10;
                preco += preco*0.05;
            } 
        }
        return Integer.toString(preco);
    }
}
