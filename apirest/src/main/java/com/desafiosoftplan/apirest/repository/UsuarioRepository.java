package com.desafiosoftplan.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiosoftplan.apirest.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findById(long cd_usuario);
}
