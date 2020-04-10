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
    
    @Query(value="SELECT * FROM processo WHERE cd_usuario_finaliza = :cd_usuario_finaliza" , nativeQuery = true) 
    List<Processo> findByUsuarioParecer(@Param("cd_usuario_finaliza") long cd_usuario_finaliza); 

}
