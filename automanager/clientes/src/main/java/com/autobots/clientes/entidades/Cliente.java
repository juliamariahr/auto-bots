package com.autobots.clientes.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;


import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Entity
public class Cliente extends RepresentationModel<Cliente> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nome;
	@Column
	private String nomeSocial;
	@Column
	private Date dataNascimento;
	@Column
	private Date dataCadastro;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Documento> documentos = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<>();

}