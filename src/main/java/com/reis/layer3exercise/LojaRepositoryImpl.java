package com.reis.layer3exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
//Classe de acesso a dados, responsável por interagir com o banco de dados em memória
//para realizar o CRUD (Criação, leitura, atualização e deleção).
@
Repository
public class LojaRepositoryImpl implements ILojaRepository {
    private ArrayList<Produto> list = new ArrayList<>();
    
    //retorna uma lista de todos os produtos cadastrados no sistema.
    public List<Produto> getCatalogo() {
        return list;
    }
    
    //retorna lista de produtos disponíveis no estoque.
    @Override 
    public List<Produto> getEstoque(){
        return list.stream().filter(p -> p.getQtd() > 0).collect(Collectors.toList());
    }
    
    //Método para registro de produtos no sistema, recebe como parâmetro objeto Produto.
    public void setProduto(Produto produto){
        boolean aux = list.stream().anyMatch(item -> item.getId() == produto.getId());
        if(!aux){
            list.add(produto);
        } else {
            list.forEach(p -> {
                if(p.getId() == produto.getId()){
                    p.acrescentaQtd(produto.getQtd());
                }
            });
        }
    }
        
    //Método para venda de produtos, recebe como parâmetro o id e a quantidade do Produto, checa se o estoque contém a quantidade requerida e retorna o cálculo do preço para a classe de serviços.
    public int venderProd(int id, int quantidade){
        for (Produto p : list){
            if(p.getId() == id && p.getQtd() >= quantidade){
                p.decrescentaQtd(quantidade);
                return p.getPreco();
            }
        }
        return -1;
    }
    
    //Método que retorna uma lista de produtos que estão com a quantidade abaixo de 3.
    public List<Integer> prodsAbaixo(){
        return list.stream()
                .filter(x -> x.getQtd() < 3)
                .map(Produto::getId)
                .collect(Collectors.toList());
    }
}
