package com.desafiosoftplan.apirest.resources;

import java.util.List;

/*
 * Bean Controller para as requisições Rest com métodos
 * Get, Post e Put associados com a Entidade Processo
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

import com.desafiosoftplan.apirest.models.Processo;
import com.desafiosoftplan.apirest.repository.ProcessoRepository;

@RestController
@RequestMapping(value="/api")
public class ProcessoResource {

	
	@Autowired
	ProcessoRepository processoRepository;
	
	@GetMapping("/processos")
	public List<Processo> listaProcessos(){
		return processoRepository.findAll();
	}

	@GetMapping("/processo/{cd_processo}")
	public Processo processoUnico(@PathVariable(value="cd_processo") long cd_processo){
		return (Processo) processoRepository.findById(cd_processo);
	}
	
	@GetMapping("/processoBuscaNumero")
	@ResponseBody
	public Processo processosNumero(@RequestParam String numProcesso){		
		return processoRepository.findByNumeroProcesso(""+numProcesso+"");
	}
	
	@GetMapping("/processoBuscaDescricao")
	@ResponseBody
	public List<Processo> listaProcessosDescricao(@RequestParam String dsProcesso){		
		return processoRepository.findByDescricaoProcesso("%"+dsProcesso+"%");
	}
	
	@GetMapping("/processoBuscaUsuarioFinaliza")
	@ResponseBody
	public List<Processo> listaProcessosUsuarioFinaliza(@RequestParam long cd_usuario_finaliza){		
		return processoRepository.findByUsuarioParecer(cd_usuario_finaliza);
	}
	
	@PostMapping("/processo")
	public Processo salvaNovoProcesso(@RequestBody Processo processo){
		return processoRepository.save(processo);
	}	
	
	@PutMapping(value="/processo")
	public Processo atualizaprocesso(@RequestBody Processo processo) {	   
	   return processoRepository.save(processo);	   
	}	
	
	@DeleteMapping(path ={"/processo/{cd_processo}"})
	public ResponseEntity<?> deletaProcesso(@PathVariable long cd_processo) {
		processoRepository.deleteById(cd_processo);
	    return ResponseEntity.ok().build();
	}
}
