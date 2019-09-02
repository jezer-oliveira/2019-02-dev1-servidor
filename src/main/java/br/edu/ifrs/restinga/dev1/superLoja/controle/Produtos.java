/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.controle;

import br.edu.ifrs.restinga.dev1.superLoja.modelo.servico.ProdutoServico;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Produto;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jezer
 */
@RestController
@RequestMapping("/api")
public class Produtos {
    
    @Autowired
    ProdutoServico produtoServico;
    
    @PostMapping("/produtos/")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoServico.cadastrar(produto);
    }
    
    @GetMapping("/produtos/")
    @ResponseStatus(HttpStatus.OK)
    //@RequestMapping(method = RequestMethod.GET, path = "/listarProdutos")
    public Iterable<Produto> listarProdutos() {
    return produtoServico.listar();
    }
    
    @PutMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable int id, @RequestBody Produto produto) {
        produto.setId(id);
        produtoServico.atualizar(produto);
    }
    
    @DeleteMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProduto(@PathVariable int id) {
        produtoServico.excluir(id);
    }

    @GetMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto recuperarProduto(@PathVariable int id) throws Throwable  {
         return produtoServico.recuperar(id);
    }

        
    /*
    @GetMapping("/produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produto> recuperarProduto(@PathVariable int id) {
        
        
        Optional<Produto> optionalProduto = produtoDAO.findById(id);
        //HttpStatus.OK);
        if(optionalProduto.isPresent())
            return ResponseEntity.ok(optionalProduto.get());
        else 
            return ResponseEntity.notFound()
                    //.header("cabecalho-customizado","treco")
                    //.header("cabecalho-customizado2","treco2")
                    .build();  //new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }*/
}
