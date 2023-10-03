package com.reis.layer3exercise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/pedidos")
    @CrossOrigin(origins="*")
    public Iterable<Produto> precos(){
        return loja.getAllProdutos();
    }

    @PostMapping("/novoproduto")
    @CrossOrigin(origins = "*")
    public boolean cadastraProdutoNovo(@RequestBody final Produto produto){
        loja.cadastraProduto(produto);
        return true;
    }

    @PostMapping("/venda")
    @CrossOrigin(origins = "*")
    public boolean venda(@RequestParam("id") int id, @RequestParam("quantidade") int quantidade){
        loja.venda(id,quantidade);
        return true;
    }
}
