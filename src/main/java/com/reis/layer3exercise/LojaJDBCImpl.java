package com.reis.layer3exercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//Classe de acesso a dados, responsável por interagir com o banco de dados utilizando operações sql
//para realizar o CRUD (Criação, leitura, atualização e deleção).

@Repository
public class LojaJDBCImpl implements ILojaRepository {
    private JdbcTemplate template;
    
    @Autowired
    public LojaJDBCImpl(JdbcTemplate template) {this.template = template;}


    //retorna lista de produtos disponíveis no estoque.
    @Override
    public Iterable<Produto> getEstoque() {
        String sql = "Select id, descricao, preco, quantidade FROM Produto Where quantidade>0";
        return this.template.query(sql, (rs, rowNum) -> (new Produto(rs.getInt("id"), rs.getString("descricao"), rs.getInt("preco"), rs.getInt("quantidade"))));
    };

    //retorna uma lista de todos os produtos cadastrados no sistema.
    @Override
    public List<Produto> getCatalogo() {
        
        List<Produto> resp = this.template.query("SELECT * from produto", (rs, rowNum) -> new Produto(rs.getInt("id"),rs.getString("descricao"), rs.getInt("preco"),rs.getInt("quantidade")));
        return resp;
    }

    //Método para registro de produtos no sistema, recebe como parâmetro objeto Produto.
    @Override
    public void setProduto(Produto produto) {
        String sql = "Insert INTO Produto (id, descricao, preco, quantidade) VALUES (?,?,?,?)";
        this.template.update(sql, produto.getId(),produto.getDescricao(),produto.getPreco(),produto.getQtd());
    }

    //Método para venda de produtos, recebe como parâmetro o id e a quantidade do Produto, checa se o estoque contém a quantidade requerida e retorna o cálculo do preço para a classe de serviços.
    @Override
    public int venderProd(int id, int quantidade) {
        String sql = "SELECT id, descricao, preco, quantidade FROM Produto WHERE id = ?";

       
        Produto p = template.queryForObject(sql, (rs, rowNum) -> (new Produto(rs.getInt("id"),rs.getString("descricao"),rs.getInt("preco"), rs.getInt("quantidade"))), id);
        
        if(p != null && p.getQtd() > 0) {
        int precoTotal = p.getPreco() * quantidade;
        
        String updateSql = "UPDATE produto SET quantidade = ? WHERE id = ?";
        template.update(updateSql, p.getQtd()-quantidade, id);
        
        return precoTotal; 
        }
        return -1;
    }
}
