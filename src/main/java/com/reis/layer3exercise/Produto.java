package com.reis.layer3exercise;

public class Produto {
    private int id;
    private String descricao;
    private int preco;
    private int qtd;

    public Produto(int i, String d, int p,int q){
        this.id = i;
        this.descricao = d;
        this.preco = p;
        this.qtd = q;
    }

    public void acrescentaQtd(int qtd) {
        this.qtd += qtd;
    }
    public void decrescentaQtd(int qtd) {
        this.qtd -= qtd;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPreco() {
        return preco;
    }

    public int getQtd() {
        return qtd;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", qtd=" + qtd + "]";
    }
    
    
}
