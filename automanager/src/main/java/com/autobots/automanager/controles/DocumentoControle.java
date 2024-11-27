package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.modelo.DocumentoSelecionador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private DocumentoRepositorio repositorio;

    @Autowired
    private DocumentoSelecionador selecionador;

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obterDocumento(@PathVariable long id) {
        List<Documento> documentos = repositorio.findAll();
        Documento documento = selecionador.selecionar(documentos, id);
        if (documento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(documento);
    }

    @GetMapping
    public ResponseEntity<List<Documento>> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        if (documentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(documentos);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrarDocumento(@RequestBody Documento documento) {
        repositorio.save(documento);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Void> atualizarDocumento(@RequestBody Documento atualizacao) {
        Documento documento = repositorio.findById(atualizacao.getId()).orElse(null);
        if (documento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(documento, atualizacao);
        repositorio.save(documento);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Void> excluirDocumento(@RequestBody Documento exclusao) {
        Documento documento = repositorio.findById(exclusao.getId()).orElse(null);
        if (documento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        repositorio.delete(documento);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
