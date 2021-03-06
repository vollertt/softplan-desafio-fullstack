package com.desafiosoftplan.apirest.resources;

import java.util.Date;
import java.util.List;

/*
 * Bean Controller para as requisições Rest com métodos
 * Get, Post e Put associados com a Entidade Processo
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

import com.desafiosoftplan.apirest.models.Processo;
import com.desafiosoftplan.apirest.repository.ProcessoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST de Processos")
@CrossOrigin(origins="*")
public class ProcessoResource {

	
	@Autowired
	ProcessoRepository processoRepository;
	
	@GetMapping("/processos")
	@ApiOperation(value="Retorna Lista de Processos Cadastrados")
	public List<Processo> listaProcessos(){
		return processoRepository.findAll();
	}

	@GetMapping("/processo/{cd_processo}")
	@ApiOperation(value="Busca Processo por código (chave)")
	public Processo processoUnico(@PathVariable(value="cd_processo") long cd_processo){
		return (Processo) processoRepository.findById(cd_processo);
	}
	
	@GetMapping("/processoBuscaNumero")
	@ApiOperation(value="Busca Processo pelo número do processo")
	@ResponseBody
	public Processo processosNumero(@RequestParam String numProcesso){		
		return processoRepository.findByNumeroProcesso(""+numProcesso+"");
	}
	
	@GetMapping("/processoBuscaDescricao")
	@ApiOperation(value="Busca Processo pela descrição do processo")
	@ResponseBody
	public List<Processo> listaProcessosDescricao(@RequestParam String dsProcesso){		
		return processoRepository.findByDescricaoProcesso("%"+dsProcesso+"%");
	}	
	
	@GetMapping("/processoBuscaStatus")
	@ApiOperation(value="Busca Processo pelo Status do Parecer : true=finalizado, false=aberto")
	@ResponseBody
	public List<Processo> listaProcessosUsuarioDistribuidor(@RequestParam Boolean status){		
		return processoRepository.findByProcessoStatus(status);
	}
	
	@GetMapping("/processoBuscaFinalizador")
	@ApiOperation(value="Busca Processo do usuário Finalizador Logado e Status do Parecer : true=finalizado, false=aberto")
	@ResponseBody
	public List<Processo> listaProcessosUsuarioParecer(@RequestParam String email, @RequestParam Boolean status){		
		return processoRepository.findByUsuarioParecer(email,status);
	}
	
	
	@PostMapping("/processo")
	@ApiOperation(value="Cadastra novo processo")
	public Processo salvaNovoProcesso(@RequestBody Processo processo){	
		Date date = new Date();
		processo.setDt_processo(date);	
		return processoRepository.save(processo);
	}	
	
	@PutMapping(value="/processo")
	@ApiOperation(value="Atualiza processo")
	public Processo atualizaprocesso(@RequestBody Processo processo) {	
	   Date date = new Date();
	   processo.setDt_parecer(date);		   
	   return processoRepository.save(processo);	   
	}	
	
	
}
