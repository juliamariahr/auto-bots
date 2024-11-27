package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.modelo.EnderecoSelecionador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;

    @Autowired
    private EnderecoSelecionador selecionador;

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> obterEndereco(@PathVariable long id) {
        List<Endereco> enderecos = enderecoRepositorio.findAll();
        Endereco endereco = selecionador.selecionar(enderecos, id);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(endereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> obterEnderecos() {
        List<Endereco> enderecos = enderecoRepositorio.findAll();
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(enderecos);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrarEndereco(@RequestBody Endereco endereco) {
        enderecoRepositorio.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizarEndereco(@RequestBody Endereco atualizacao) {
        Endereco endereco = enderecoRepositorio.findById(atualizacao.getId()).orElse(null);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(endereco, atualizacao);
        enderecoRepositorio.save(endereco);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Void> excluirEndereco(@RequestBody Endereco exclusao) {
        Endereco endereco = enderecoRepositorio.findById(exclusao.getId()).orElse(null);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        enderecoRepositorio.delete(endereco);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
