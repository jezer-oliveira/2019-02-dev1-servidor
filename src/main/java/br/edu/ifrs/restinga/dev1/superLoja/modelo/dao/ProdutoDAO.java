/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.modelo.dao;

import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Produto;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jezer
 */
public  interface ProdutoDAO extends CrudRepository<Produto, Integer> {
    
    public List<Produto> findByNomeStartingWith(String nome);
    public List<Produto> findByGeneroIsNotNull();
    public List<Produto> findByGeneroNomeStartingWith(String nome);
    public List<Produto> findByFornecedoresNomeStartingWith(String nome);
    
}
