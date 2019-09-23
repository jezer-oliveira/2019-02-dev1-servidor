/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.controle;

import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Fornecedor;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Modelo;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.servico.ProdutoServico;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Produto;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.servico.Servico;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jezer
 */
@RestController
@RequestMapping("/api/produtos")
public class Produtos extends CRUDControle<Produto> {

    @Autowired
    ProdutoServico servico;

    @Override
    public Servico<Produto> getService() {
        return servico;
    }

    @PostMapping("/{idProduto}/modelos/")
    @ResponseStatus(HttpStatus.CREATED)
    public Modelo cadastrarModelo(@PathVariable int idProduto, @RequestBody Modelo modelo) throws Throwable {
        return servico.cadastrarModelo(idProduto, modelo);
    }

    @PutMapping("/{idProduto}/modelos/{idModelo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarModelo(@PathVariable int idProduto, @PathVariable int idModelo, @RequestBody Modelo modelo) throws Throwable {
        modelo.setId(idModelo);
        servico.atualizarModelo(idProduto, modelo);
    }

    @DeleteMapping("/{idProduto}/modelos/{idModelo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirModelo(@PathVariable int idProduto, @PathVariable int idModelo) throws Throwable {
        servico.excluirModelo(idProduto, idModelo);
    }

    @GetMapping("/{idProduto}/modelos/{idModelo}")
    @ResponseStatus(HttpStatus.OK)
    public Modelo recuperarModelo(@PathVariable int idProduto, @PathVariable int idModelo) throws Throwable {
        return servico.recuperarModelo(idProduto, idModelo);
    }

    
    @GetMapping("/{idProduto}/modelos/")
    @ResponseStatus(HttpStatus.OK)
    public List<Modelo> listarModelo(@PathVariable int idProduto) throws Throwable {
        return servico.listarModelo(idProduto);
    }

    @RequestMapping(path = "/{idProduto}/fornecedores/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void associarFornecedor(@PathVariable int idProduto, @RequestBody Fornecedor fornecedor) throws Throwable {
        servico.associarFornecedor(idProduto, fornecedor);
        
    }

    @DeleteMapping("/{idProduto}/fornecedores/{idFornecedor}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarFornecedor(@PathVariable int idProduto, @PathVariable int idFornecedor) throws Throwable {
         servico.desassociarFornecedor(idProduto,idFornecedor);
    }

    
    @RequestMapping(path = "/{idProduto}/fornecedores/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Fornecedor> listarFornecedor(@PathVariable int idProduto) throws Throwable {
        return this.recuperar(idProduto).getFornecedores();
    }
    
    @GetMapping("/{idProduto}/fornecedores/{idFornecedor}")
    @ResponseStatus(HttpStatus.OK)
    public Fornecedor recuperarFornecedor(@PathVariable int idProduto, @PathVariable int idFornecedor) throws Throwable {
        return servico.recuperarFornecedor(idProduto,idFornecedor);
    }

    @GetMapping("/pesquisar/nome/inicia/{nome}")
    public List<Produto> findByNomeStartingWith(@PathVariable String nome) {
        return servico.findByNomeStartingWith(nome);
    }

    @GetMapping("/pesquisar/genero/naoNulo")
    public List<Produto> findByGeneroIsNotNull() {
        return servico.findByGeneroIsNotNull();
    }

    @GetMapping("/pesquisar/genero/nome/{nome}")
    public List<Produto> findByGeneroNomeStartingWith(@PathVariable String nome) {
        return servico.findByGeneroNomeStartingWith(nome);
    }
    @GetMapping("/pesquisar/fornecedores/nome/{nome}")
    public List<Produto> findByFornecedoresNomeStartingWith(@PathVariable String nome) {
        return servico.findByFornecedoresNomeStartingWith(nome);
    }
    
    

}
