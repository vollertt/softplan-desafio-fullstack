package com.desafiosoftplan.apirest.resources;

import java.util.List;

/*
 * Bean Controller para as requisições Rest com métodos
 * Get, Post e Put associados com a Entidade Usuário
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/usuarioBusca")
	@ResponseBody
	public List<Usuario> listaUsuariosBusca(@RequestParam String searchField){		
		return usuarioRepository.findByNameOrEmail("%"+searchField+"%");
	}
	
	@PostMapping("/usuario")
	public Usuario salvaNovoUsuario(@RequestBody Usuario usuario){
		return usuarioRepository.save(usuario);
	}	
	
	@PutMapping(value="/usuario")
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {	   
	   return usuarioRepository.save(usuario);	   
	}	
	
	@DeleteMapping(path ={"/usuario/{cd_usuario}"})
	public ResponseEntity<?> deletaUsuario(@PathVariable long cd_usuario) {
		usuarioRepository.deleteById(cd_usuario);
	    return ResponseEntity.ok().build();
	}
}
