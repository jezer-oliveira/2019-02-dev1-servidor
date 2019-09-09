/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.modelo.rn;

import br.edu.ifrs.restinga.dev1.superLoja.excecoes.QuebraRegraNegocio;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.dao.ProdutoDAO;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Fornecedor;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Produto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jezer
 */

@Component
public class FornecedorRN implements  RegraNegocio<Fornecedor> {
    
    
    @Autowired
    ProdutoDAO produtoDAO;
    
    @Override
    public void validarCadastrar(Fornecedor entidade) {
        if(entidade.getCnpj()==null||entidade.getCnpj().length()!=14)
            throw new QuebraRegraNegocio("CNPJ deve ter 14 caracteres");
    }

    @Override
    public void validarAtualizar(Fornecedor entidadeAntiga, Fornecedor entidadeAntigaNova) {
        if(! entidadeAntiga.getCnpj().equals(entidadeAntigaNova.getCnpj()))
            throw new QuebraRegraNegocio("CNPJ não pode ser alterado");

    }

    @Override
    public void validarExcluir(Fornecedor entidade) {
        Iterable<Produto> findAll = produtoDAO.findAll();
        for (Produto produto : findAll) {
            List<Fornecedor> fornecedores = produto.getFornecedores();
            for (Fornecedor fornecedor : fornecedores) {
                if(fornecedor.getId()==entidade.getId())
                    throw new QuebraRegraNegocio("Não pode ser excluído fornecedor com produto cadastrado.");
            }
        }
    }
    
    
}
