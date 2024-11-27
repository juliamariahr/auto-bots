package com.autobots.pecas.service;


import com.autobots.pecas.entidades.Venda;
import com.autobots.pecas.repositorios.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepositorio repositorio;

    public Long create(Venda venda) {
        return repositorio.save(venda).getId();
    }

    public List<Venda> findAll(){
        List<Venda> vendas = repositorio.findAll();
        return vendas;
    }

    public Venda findById(Long id) {
        Venda venda = repositorio.findById(id).orElse(null);
        return venda;
    }

    public Venda update(Venda vendaAtualizacao) {
        Venda vendaExistente = findById(vendaAtualizacao.getId());
        vendaAtualizacao.setId(vendaExistente.getId());
        vendaAtualizacao.setCadastro(vendaExistente.getCadastro());
        vendaAtualizacao.setIdentificacao(vendaExistente.getIdentificacao());
        vendaAtualizacao.setFuncionario(vendaExistente.getFuncionario());

        if (vendaAtualizacao.getMercadorias() == null){
            vendaAtualizacao.setMercadorias(vendaExistente.getMercadorias());
        }
        if (vendaAtualizacao.getServicos() == null){
            vendaAtualizacao.setServicos(vendaExistente.getServicos());
        }
        if (vendaAtualizacao.getVeiculo() == null){
            vendaAtualizacao.setVeiculo(vendaExistente.getVeiculo());
        }
        return repositorio.save(vendaAtualizacao);
    }

    public void delete(Venda venda) {
        repositorio.delete(venda);
    }
}
