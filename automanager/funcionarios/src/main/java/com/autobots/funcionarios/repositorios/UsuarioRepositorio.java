package com.autobots.funcionarios.repositorios;


import com.autobots.funcionarios.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String nome);
}
