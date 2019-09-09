/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.restinga.dev1.superLoja.modelo.servico;

import br.edu.ifrs.restinga.dev1.superLoja.excecoes.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.dao.EmbalagemDAO;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.dao.ModeloDAO;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.dao.ProdutoDAO;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Embalagem;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Fornecedor;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Modelo;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.entidade.Produto;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.rn.ProdutoRN;
import br.edu.ifrs.restinga.dev1.superLoja.modelo.rn.RegraNegocio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 *
 * @author jezer
 */
@Component
public class ProdutoServico extends Servico<Produto> {

    @Autowired
    ProdutoDAO produtoDAO;
    @Autowired
    EmbalagemDAO embalagemDAO;
    @Autowired
    ProdutoRN produtoRN;

    @Autowired
    ModeloDAO modeloDAO;

    @Override
    public CrudRepository<Produto, Integer> getDAO() {
        return produtoDAO;
    }

    @Override
    public RegraNegocio<Produto> getRegraNegocio() {
        return produtoRN;
    }

    @Override
    public Produto cadastrar(Produto produto) {
        produtoRN.validar(produto);
        if (produto.getEmbalagem() != null) {
            Embalagem embalagemSalva = embalagemDAO.save(produto.getEmbalagem());
            produto.setEmbalagem(embalagemSalva);
        }

        Produto produtoBanco = produtoDAO.save(produto);
        return produtoBanco;
    }

    @Override
    public void atualizar(Produto produto) {
        produtoRN.validar(produto);
        if (produto.getEmbalagem() != null) {
            embalagemDAO.save(produto.getEmbalagem());
        }
        produtoDAO.save(produto);
    }

    public Modelo cadastrarModelo(int idProduto, Modelo modelo) throws Throwable {
        Produto produto = this.recuperar(idProduto);
        modelo.setId(0);
        Modelo modeloBanco = modeloDAO.save(modelo);
        produto.getModelos().add(modeloBanco);
        produtoDAO.save(produto);
        return modeloBanco;
    }

    public Modelo recuperarModelo(int idProduto, int idModelo) throws Throwable {
        Produto produto = this.recuperar(idProduto);
        List<Modelo> modelos = produto.getModelos();
        for (Modelo modelo : modelos) {
            if (modelo.getId() == idModelo) {
                return modelo;
            }
        }
        throw new NaoEncontrado("id " + idModelo + " não foi encontrada");
    }

    public void atualizarModelo(int idProduto, Modelo modelo) throws Throwable {
        this.recuperarModelo(idProduto, modelo.getId());
        modeloDAO.save(modelo);
    }

    public List<Modelo> listarModelo(int idProduto) throws Throwable {
        return this.recuperar(idProduto).getModelos();
    }

    public void excluirModelo(int idProduto, int idModelo) throws Throwable {
        Produto produto = this.recuperar(idProduto);
        List<Modelo> modelos = produto.getModelos();
        for (Modelo modelo : modelos) {
            if (modelo.getId() == idModelo) {
                produto.getModelos().remove(modelo);
                produtoDAO.save(produto);
                return;
            }
        }
        throw new NaoEncontrado("id " + idModelo + " não foi encontrada");
    }

    public void associarFornecedor(int idProduto, Fornecedor fornecedor) throws Throwable {
        Produto produto = this.recuperar(idProduto);
        produto.getFornecedores().add(fornecedor);
        produtoDAO.save(produto);
    }

    public void desassociarFornecedor(int idProduto, int idFornecedor) throws Throwable {
        Produto produto = this.recuperar(idProduto);
        List<Fornecedor> fornecedores = produto.getFornecedores();
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == idFornecedor) {
                produto.getFornecedores().remove(fornecedor);
                produtoDAO.save(produto);
                return;
            }

        }
        throw new NaoEncontrado("id " + idFornecedor + " não foi encontrada");
    }

    public List<Fornecedor> listarFornecedor(int idProduto) throws Throwable {
        return this.recuperar(idProduto).getFornecedores();
    }

        public Fornecedor recuperarFornecedor(int idProduto, int id) throws Throwable {
        Produto produto = this.recuperar(idProduto);
        List<Fornecedor> fornecedores = produto.getFornecedores();
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                return fornecedor;
            }
        }
        throw new NaoEncontrado("id " + id + " não foi encontrada");
    }

}
