/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.controle;

import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Fornecedor;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.servico.FornecedorServico;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jezer
 */
@RestController
@RequestMapping("/api/fornecedores")
public class Fornecedores extends  CRUDControle<Fornecedor> {
    @Autowired
    FornecedorServico servico;
    
    @Override
    public Servico<Fornecedor> getService() {
        return servico;
    }
    
}
