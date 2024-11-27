package com.autobots.clientes.modelo;

import com.autobots.clientes.entidades.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoSelecionador {

    public Endereco selecionar(List<Endereco> enderecos, long id) {
        Endereco selecionado = null;
        for (Endereco endereco : enderecos) {
            if (endereco.getId() == id) {
                selecionado = endereco;
            }
        }
        return selecionado;
    }

}
