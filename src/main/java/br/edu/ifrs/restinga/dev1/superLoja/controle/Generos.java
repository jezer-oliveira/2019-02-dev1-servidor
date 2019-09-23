/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.controle;

import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Genero;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.servico.GeneroServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class Generos {
    
    @Autowired
    GeneroServico generoServico;
    
    @PostMapping("/generos/")
    @ResponseStatus(HttpStatus.CREATED)
    public Genero cadastrarGenero(@RequestBody Genero genero)  throws Throwable {
        return generoServico.cadastrar(genero);
    }
    
    @GetMapping("/generos/")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Genero> listarGeneros() {
    return generoServico.listar();
    }

}
