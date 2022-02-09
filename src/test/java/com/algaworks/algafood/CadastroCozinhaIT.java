package com.algaworks.algafood;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {
	
//	@Autowired
//	private CadastroCozinhaService cadastroCozinha;
	
	@LocalServerPort
	private int port;
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
		.basePath("/cozinhas")
		.port(port)
		.accept(ContentType.JSON)
				.when()
				.get()
				.then()
				.statusCode(HttpStatus.OK.value());
		
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
