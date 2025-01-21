package br.com.alura.codechella.controller;

import br.com.alura.codechella.dto.EventoDto;
import br.com.alura.codechella.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;
    private final Sinks.Many<EventoDto> eventosSink = Sinks.many().multicast().onBackpressureBuffer();


    @GetMapping//(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //não vamos usar o list, pois o flux é não bloqueante
    public Flux<EventoDto> obterTodos() {
        return eventoService.obterTodos();
    }

    @GetMapping("/{id}")
    //não vamos usar o list, pois o flux é não bloqueante
    public Mono<EventoDto> obterPorId(@PathVariable Long id) {
        return eventoService.obterPorId(id);
    }

    @GetMapping(value = "/categoria/{tipo}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<EventoDto> obterPorTipo(@PathVariable String tipo){
        return Flux.merge(eventoService.obterPorTipo(tipo), eventosSink.asFlux())
                .delayElements(Duration.ofSeconds(4));
    }

    @PostMapping
    public Mono<EventoDto> cadastrar(@RequestBody EventoDto eventoDto) {
        return eventoService.cadastrar(eventoDto).doOnSuccess(e -> eventosSink.tryEmitNext(eventoDto));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> excluir(@PathVariable Long id) {
        return eventoService.excluir(id);
    }

    @PutMapping("/{id}")
    public Mono<EventoDto> alterar(@PathVariable Long id, @RequestBody EventoDto dto){
        return eventoService.alterar(id, dto);
    }

}
