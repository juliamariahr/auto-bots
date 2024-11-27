package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Endereco obterEndereco(@PathVariable long id) {
		List<Endereco> enderecos = enderecoRepositorio.findAll();
		return selecionador.selecionar(enderecos, id);
	}

	@GetMapping
	public List<Endereco> obterEnderecos() {
		List<Endereco> enderecos = enderecoRepositorio.findAll();
		return enderecos;
	}

	@PostMapping("/cadastro")
	public void cadastrarEndereco(@RequestBody Endereco Endereco) {
		enderecoRepositorio.save(Endereco);
	}

	@PutMapping("/atualizar")
	public void atualizarEndereco(@RequestBody Endereco atualizacao) {
		Endereco Endereco = enderecoRepositorio.getById(atualizacao.getId());
		EnderecoAtualizador atualizador = new EnderecoAtualizador();
		atualizador.atualizar(Endereco, atualizacao);
		enderecoRepositorio.save(Endereco);
	}

	@DeleteMapping("/excluir")
	public void excluirEndereco(@RequestBody Endereco exclusao) {
		Endereco Endereco = enderecoRepositorio.getById(exclusao.getId());
		enderecoRepositorio.delete(Endereco);
	}
}
