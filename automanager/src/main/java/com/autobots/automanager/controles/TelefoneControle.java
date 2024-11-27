package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.modelo.TelefoneSelecionador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private TelefoneSelecionador selecionador;

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterTelefone(@PathVariable long id) {
        List<Telefone> telefones = repositorio.findAll();
        Telefone telefone = selecionador.selecionar(telefones, id);
        if (telefone == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(telefone);
    }

    @GetMapping
    public ResponseEntity<List<Telefone>> obterTelefones() {
        List<Telefone> telefones = repositorio.findAll();
        if (telefones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(telefones);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrarTelefone(@RequestBody Telefone telefone) {
        repositorio.save(telefone);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizarTelefone(@RequestBody Telefone atualizacao) {
        Telefone telefone = repositorio.findById(atualizacao.getId()).orElse(null);
        if (telefone == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);
        repositorio.save(telefone);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Void> excluirTelefone(@RequestBody Telefone exclusao) {
        Telefone telefone = repositorio.findById(exclusao.getId()).orElse(null);
        if (telefone == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        repositorio.delete(telefone);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
