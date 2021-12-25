package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	// @ResponseStatus(value = HttpStatus.CREATED)
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {
		
		return cadastroCozinha.buscarOuFalhar(cozinhaId);
		//return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException("aaaa"));
		
//		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
//
//
//		if (cozinha.isPresent()) {
//			return ResponseEntity.ok(cozinha.get());
//		}
//
//		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public Cozinha atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		
		Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);
		
		//Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

		//if (cozinhaAtual.isPresent()) {
			// cozinhaAtual.setNome(cozinha.getNome());
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			
			return cadastroCozinha.salvar(cozinhaAtual);
			//Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());

			//return ResponseEntity.ok(cozinhaSalva);
		}

		//return ResponseEntity.notFound().build();

	//}

//	@DeleteMapping("/{cozinhaId}")
//	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
//
//		try {
//			cadastroCozinha.excluir(cozinhaId);
//
//			return ResponseEntity.noContent().build();
//		}catch(EntidadeNaoEncontradaException e) {
//			return ResponseEntity.notFound().build();
//		}
//		catch(EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//		}
//
//	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		//try {
			cadastroCozinha.excluir(cozinhaId);
			
//		}catch(EntidadeNaoEncontradaException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//			//throw new ServerWebInputException(e.getMessage());
//		}

	}

}
