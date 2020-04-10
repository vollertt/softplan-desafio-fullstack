package com.desafiosoftplan.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafiosoftplan.apirest.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findById(long cd_usuario);    
    
    @Query(value="SELECT cd_usuario, dt_cadastro, email, nome, perfil_usuario, status FROM usuario WHERE email like :searchField or Lower(nome) like Lower(:searchField)" , nativeQuery = true) 
    List<Usuario> findByNameOrEmail(@Param("searchField") String searchField);    

}
