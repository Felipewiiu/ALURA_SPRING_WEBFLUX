package br.com.alura.codechella.controller;

import br.com.alura.codechella.domain.entities.Evento;
import br.com.alura.codechella.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoRepository eventoRepository;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //não vamos usar o list, pois o flux é não bloqueante
    public Flux<Evento> obterTodos() {
        return eventoRepository.findAll();
    }

}
