package com.algaworks.algafood;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	
//	@Autowired
//	private CadastroCozinhaService cadastroCozinha;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		flyway.migrate();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		
		RestAssured.given()
		.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveConter4Cozinhas_QuantoConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
		.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.body("", Matchers.hasSize(4))
				.body("nome", Matchers.hasItems("Indiana", "Tailandesa"));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
			.body("{ \"nome\": \"Chinesa\" }")
			.contentType(ContentType.JSON)
			.when()
			.post()
			.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	

//	@Test
//	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
//		//cenário
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("Chinesa");
//		
//		//ação
//		novaCozinha = cadastroCozinha.salvar(novaCozinha);
//		
//		//validação
//		assertThat(novaCozinha).isNotNull();
//		assertThat(novaCozinha.getId()).isNotNull();
//	}
//	
//	@Test
//	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//		
//		ConstraintViolationException erroEsperado =
//			      Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			         cadastroCozinha.salvar(novaCozinha);
//			      });
//		
//		assertThat(erroEsperado).isNotNull();		
//		
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
//		EntidadeEmUsoException erro = Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
//			cadastroCozinha.excluir(1L);
//		});
//		
//		assertThat(erro).isNotNull();
//	}
//	
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
//		CozinhaNaoEncontradaException erro = Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
//			cadastroCozinha.excluir(101L);
//		});
//		
//		assertThat(erro).isNotNull();
//	}


}
