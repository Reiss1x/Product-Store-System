package com.reis.layer3exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class LojaRepositoryImpl implements ILojaRepository {
    private ArrayList<Produto> list = new ArrayList<>();

    public List<Produto> getAll() {
        return list;
    }
    @Override 
    public List<Produto> getCatalogo(){
        return list.stream().filter(p -> p.getQtd() > 0).collect(Collectors.toList());
    }
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
    public int venderProd(int id, int quantidade){
        for (Produto p : list){
            if(p.getId() == id && p.getQtd() >= quantidade){
                p.decrescentaQtd(quantidade);
                return p.getPreco();
            }
        }
        return -1;
    }
    public List<Integer> prodsAbaixo(){
        return list.stream()
                .filter(x -> x.getQtd() < 3)
                .map(Produto::getId)
                .collect(Collectors.toList());
    }
}
