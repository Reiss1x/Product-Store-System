package com.reis.layer3exercise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



//Classe controller responsável pelas solicitações dos clientes e retornar respostas apropriadas.
@RestController
public class LojaController {

    LojaService loja;
    
    
    @Autowired
    public LojaController(LojaService loja){
        this.loja = loja;
    }

    @GetMapping("/")
    @CrossOrigin(origins="*")
    public String preco(){
        return "Bem vindo a Loja.";
    }

    @GetMapping("/estoque")
    @CrossOrigin(origins="*")
    public Iterable<Produto> estoque(){
        return loja.getEstoque();
    }
    @GetMapping("/catalogo")
    @CrossOrigin(origins="*")
    public Iterable<Produto> catalogo(){
        return loja.getCatalogo();
    }

    @PostMapping("/novoproduto")
    @CrossOrigin(origins = "*")
    public boolean cadastraProdutosNovos(@RequestBody List<Produto> produtos){
        for(Produto p : produtos){
            loja.cadastraProduto(p);
        }
        return true;
    }

    @PostMapping("/venda")
    @CrossOrigin(origins = "*")
    public String venda(@RequestParam("id") int id, @RequestParam("quantidade") int quantidade){
        String aux = loja.venda(id,quantidade);
        return aux;
    }
}
