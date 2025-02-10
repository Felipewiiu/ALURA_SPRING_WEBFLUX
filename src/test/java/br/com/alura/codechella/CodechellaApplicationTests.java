package br.com.alura.codechella;

import br.com.alura.codechella.domain.enums.TipoEvento;
import br.com.alura.codechella.dto.EventoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CodechellaApplicationTests {

	@Autowired
	private WebTestClient webTestClient; // substitui o restTemplate

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void cadastraNovoEvento() {
		EventoDto dto = new EventoDto(null, TipoEvento.SHOW, "Kiss",
				LocalDate.parse("2025-01-01"), "Show da melhor banda que existe");

		webTestClient.post().uri("/eventos").bodyValue(dto)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(EventoDto.class).value(response -> {
					assertNotNull(response.id());
					assertEquals(dto.evento(), response.evento());
					assertEquals(dto.nome(), response.nome());
					assertEquals(dto.data(), response.data());

				});
	}

	@Test
	void buscarEvento() {
		EventoDto dto = new EventoDto(13L, TipoEvento.SHOW, "The Weeknd",
				LocalDate.parse("2025-11-02"), "Um show eletrizante ao ar livre com muitos efeitos especiais.");

		webTestClient.get().uri("/eventos")
				.exchange()
				.expectStatus().is2xxSuccessful()
				// desolve vários ítens
				.expectBodyList(EventoDto.class).value(response -> {
					EventoDto eventoDto = response.get(12);
					assertEquals(dto.evento(), eventoDto.evento());
					assertEquals(dto.nome(), eventoDto.nome());
					assertEquals(dto.data(), eventoDto.data());

				});
	}



}
