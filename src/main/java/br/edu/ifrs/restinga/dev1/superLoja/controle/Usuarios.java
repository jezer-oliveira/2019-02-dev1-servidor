package br.edu.ifrs.restinga.dev1.superLoja.controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ifrs.restinga.dev1.superLoja.auth.AuthUser;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Usuario;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.servico.UsuarioServico;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jezer
 */
@RestController
@RequestMapping(path = "/api")
public class Usuarios {

    @PreAuthorize("hasAuthority('administrador')")
    @RequestMapping(path = "/usuarios", method = RequestMethod.GET)
    public Iterable<Usuario> listar() {
        return usuarioServico.listar();
    }

    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.GET)
    public Usuario recuperar(@AuthenticationPrincipal AuthUser usuarioAut,  @PathVariable int id) {
       return usuarioServico.recuperar(usuarioAut, id);
    }

    
    @Autowired
    UsuarioServico usuarioServico;
    @RequestMapping(path = "/usuarios/", method = RequestMethod.POST)
    public Usuario inserir(@AuthenticationPrincipal AuthUser usuarioAut, @RequestBody Usuario usuario) {
        return usuarioServico.cadastrar(usuarioAut, usuario);
    }


    @RequestMapping(path = "/usuarios/login/", method = RequestMethod.GET)
    public ResponseEntity<Usuario> loginToken(@RequestParam String login, @RequestParam String senha) throws UnsupportedEncodingException {
        Usuario usuario = usuarioServico.login(login, senha);
        String token = usuarioServico.token(usuario);
        return ResponseEntity.ok().header("token", token).body(usuario);
    }

}
