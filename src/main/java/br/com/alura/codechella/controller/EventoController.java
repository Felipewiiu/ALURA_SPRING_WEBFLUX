package br.com.alura.codechella.controller;

import br.com.alura.codechella.dto.EventoDto;
import br.com.alura.codechella.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping(produces = MediaType.)
    //não vamos usar o list, pois o flux é não bloqueante
    public Flux<EventoDto> obterTodos() {
        return eventoService.obterTodos();
    }

}
