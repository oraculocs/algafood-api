package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	public List<Cidade> listar(){
		List<Cidade> lista = cidadeRepository.findAll();
		return lista;
	}
	
	public Cidade buscar(Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		return cidade.get();
	}
	
	public Cidade salvar(Cidade cidade) {
		cidade = cidadeRepository.save(cidade);
		return cidade;
	}
	
	public void remover(Long id) {
		try {
			cidadeRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Cidade com código %d", id));
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
		}
	}

}
