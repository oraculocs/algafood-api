package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<Estado> listar(){
		return cadastroEstadoService.listar();
	}
	
	@GetMapping("/{id}")
	public Estado buscar(@PathVariable Long id){
		
		return cadastroEstadoService.buscarOuFalhar(id);
		//Estado estado = cadastroEstadoService.buscar(id);
		
//		if(estado != null) {
//			return ResponseEntity.ok(estado);
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado);
	}
	
	@PutMapping("/{id}")
	public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado){
		
		Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(id);
		//Estado estadoAtual = cadastroEstadoService.buscar(id);
		
//		if(estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			
			return cadastroEstadoService.salvar(estadoAtual);
//			cadastroEstadoService.salvar(estadoAtual);
//		
//			return ResponseEntity.ok(estadoAtual);
//		}
//		
//		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		//try {
			cadastroEstadoService.excluir(id);
			
			//return ResponseEntity.noContent().build();
//		}catch(EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		}catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT)
//					.body(e.getMessage());
//		}
	}

}
