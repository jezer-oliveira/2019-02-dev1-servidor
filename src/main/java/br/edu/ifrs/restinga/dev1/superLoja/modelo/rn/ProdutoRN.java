/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.modelo.rn;

import br.edu.ifrs.restinga.dev1.superLoja.excecoes.QuebraRegraNegocio;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Produto;
import org.springframework.stereotype.Component;

/**
 *
 * @author jezer
 */

@Component
public class ProdutoRN {
    public void validar(Produto produto) {
        if(produto.getValor()<=0) 
            throw new QuebraRegraNegocio("valor deve ser maior que 0");
        if(produto.getNome()==null||produto.getNome().trim().length()<3)
            throw new QuebraRegraNegocio("Nome deve ter 3 ou mais letras");
        // Caso seja obrigatorio a embalgem
        //if(produto.getEmbalagem()==null)            throw new QuebraRegraNegocio("Embalagem não pode ser nulo");
        
    }
}
