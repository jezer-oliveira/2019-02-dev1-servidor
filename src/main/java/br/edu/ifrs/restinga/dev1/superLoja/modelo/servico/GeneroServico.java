/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.modelo.servico;

import br.edu.ifrs.restinga.dev1.superLoja.modelo.dao.GeneroDAO;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Genero;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jezer
 */
@Component
public class GeneroServico {
    @Autowired
    GeneroDAO generoDAO;

    public Genero cadastrar(Genero genero) {
        Genero save = generoDAO.save(genero);
        return save;
        }

    public Iterable<Genero> listar() {
        return generoDAO.findAll();
    }

}