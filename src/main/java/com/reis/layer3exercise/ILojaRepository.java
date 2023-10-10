package com.reis.layer3exercise;

import java.util.List;


//interface que define o conjunto de métodos para acessar os dados do banco.
public interface ILojaRepository {
    public Iterable<Produto> getEstoque();
    public List<Produto> getCatalogo();
    public void setProduto(Produto produto);
    public int venderProd(int id, int quantidade);
}
