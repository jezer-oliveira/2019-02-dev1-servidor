/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.modelo.servico;

import br.edu.ifrs.restinga.dev1.superLoja.modelo.dao.FornecedorDAO;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Fornecedor;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.rn.FornecedorRN;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.rn.RegraNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author jezer
 */
@Component
public class FornecedorServico extends  Servico<Fornecedor> {

    @Autowired
    FornecedorDAO dao;
    
    @Autowired
    FornecedorRN regraNegocio;

    @Override
    public CrudRepository<Fornecedor, Integer> getDAO() {
        return  dao;
    }

    @Override
    public RegraNegocio<Fornecedor> getRegraNegocio() {
        return regraNegocio;
    }
   
}
