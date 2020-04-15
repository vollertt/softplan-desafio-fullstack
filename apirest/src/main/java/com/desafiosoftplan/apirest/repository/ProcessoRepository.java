package com.desafiosoftplan.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.desafiosoftplan.apirest.models.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long>{
	
    Processo findById(long cd_processo);    
        
    @Query(value="SELECT * FROM processo WHERE num_processo like :numProcesso" , nativeQuery = true) 
    Processo findByNumeroProcesso(@Param("numProcesso") String numProcesso);    
    
    @Query(value="SELECT * FROM processo WHERE Lower(ds_processo) like Lower(:dsProcesso)" , nativeQuery = true) 
    List<Processo> findByDescricaoProcesso(@Param("dsProcesso") String dsProcesso);  
    
    @Query(value="SELECT * FROM processo p, usuario u WHERE u.cd_usuario=p.cd_usuario_cadastro and p.status_finalizado = :status Order by u.nome" , nativeQuery = true) 
    List<Processo> findByProcessoStatus(@Param("status") Boolean status); 
    
    @Query(value="SELECT * FROM processo p, usuario u WHERE ( p.cd_usuario_finaliza = u.cd_usuario) and u.email like :email and p.status_finalizado = :status Order by p.parecer DESC" , nativeQuery = true) 
    List<Processo> findByUsuarioParecer(@Param("email") String email, @Param("status") Boolean status); 

}
