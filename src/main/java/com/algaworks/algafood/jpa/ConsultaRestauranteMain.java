package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> todosRestaurantes = restaurantes.findAll();
		
		for(Restaurante restaurante: todosRestaurantes) {
			System.out.println(restaurante.getNome());
			System.out.println(restaurante.getTaxaFrete());
			System.out.println(restaurante.getCozinha().getNome());
		}
	}

}
