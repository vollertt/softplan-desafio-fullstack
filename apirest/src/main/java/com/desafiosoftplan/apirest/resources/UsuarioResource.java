package com.desafiosoftplan.apirest.resources;

import java.util.Date;
import java.util.List;

/*
 * Bean Controller para as requisições Rest com métodos
 * Get, Post e Put associados com a Entidade Usuário 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST de Usuários")
@CrossOrigin(origins="*")
public class UsuarioResource {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/usuarios")
	@ApiOperation(value="Retorna Lista dos Usuários Cadastrados")
	public List<Usuario> listaUsuarios(){
		return usuarioRepository.findAll();
	}

	@GetMapping("/usuario/{cd_usuario}")
	@ApiOperation(value="Retorna Usuário pelo código")
	public Usuario usuarioUnico(@PathVariable(value="cd_usuario") long cd_usuario){
		return (Usuario) usuarioRepository.findById(cd_usuario);
	}
	
	@GetMapping("/usuarioBusca")
	@ApiOperation(value="Retorna Lista de Usuários por busca de Email ou Nome")
	@ResponseBody
	public List<Usuario> listaUsuariosBusca(@RequestParam String searchField){		
		return usuarioRepository.findByNameOrEmail("%"+searchField+"%");
	}
	
	@GetMapping("/usuarioLogin")
	@ApiOperation(value="Busca usuário Login")
	@ResponseBody
	public Usuario usuarioLogin(@RequestParam String email){		
		return usuarioRepository.findByUsuarioLogin(email);
	}	
	
	@GetMapping("/usuarioPerfil")
	@ApiOperation(value="Busca lista de usuários ativos conforme  Perfil")
	@ResponseBody
	public List<Usuario> listaUsuarioPerfil(@RequestParam String perfilUsuario){		
		return usuarioRepository.findByUsuarioPerfil(perfilUsuario);
	}
	
	@PostMapping("/usuario")
	@ApiOperation(value="Cadastra e salva novo usuário")
	public Usuario salvaNovoUsuario(@RequestBody Usuario usuario){
		usuario.setStatus(true);
		Date date = new Date();
		usuario.setDt_cadastro(date);
		return usuarioRepository.save(usuario);
	}	
	
	@PutMapping(value="/usuario")
	@ApiOperation(value="Atualiza cadastro do usuário")
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {	   
	   return usuarioRepository.save(usuario);	   
	}	
	
	
}
