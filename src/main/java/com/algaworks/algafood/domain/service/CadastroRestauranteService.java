package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	public RestauranteRepository restauranteRepository;

	@Autowired
	public CozinhaRepository cozinhaRepository;

	public List<Restaurante> listar() {
		List<Restaurante> lista = restauranteRepository.findAll();
		return lista;
	}

	public Restaurante buscar(Long id) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if(restaurante.isPresent()) {
			return restaurante.get();
		}

		return null;
	}

	public Restaurante salvar(Restaurante restaurante) {

		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));


		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	public Restaurante atualizar(Long id, Restaurante restaurante) {
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

		if (restauranteAtual != null) {
			BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

			restauranteAtual = Optional.ofNullable(salvar(restauranteAtual.get()));

			return restauranteAtual.get();
		}
		return null;
	}
}
