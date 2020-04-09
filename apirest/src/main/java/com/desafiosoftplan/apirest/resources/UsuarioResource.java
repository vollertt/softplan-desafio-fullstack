package com.desafiosoftplan.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafiosoftplan.apirest.models.Usuario;
import com.desafiosoftplan.apirest.repository.UsuarioRepository;

@RestController
@RequestMapping(value="/api")
public class UsuarioResource {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios(){
		return usuarioRepository.findAll();
	}

	@GetMapping("/usuario/{cd_usuario}")
	public Usuario usuarioUnico(@PathVariable(value="cd_usuario") long cd_usuario){
		return (Usuario) usuarioRepository.findById(cd_usuario);
	}
}
