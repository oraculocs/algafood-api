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
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@GetMapping
	public List<Cidade> listar(){
		return cadastroCidadeService.listar();
	}

	@GetMapping("{id}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long id){
		Cidade cidade = cadastroCidadeService.buscar(id);
		
		if(cidade != null) {
			return ResponseEntity.ok(cidade);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		return cadastroCidadeService.salvar(cidade);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
		Cidade cidadeAtual = cadastroCidadeService.buscar(id);
		
		if(cidadeAtual != null) {
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			cadastroCidadeService.salvar(cidadeAtual);
			return ResponseEntity.ok(cidadeAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			cadastroCidadeService.remover(id);
			return ResponseEntity.noContent().build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}
}
