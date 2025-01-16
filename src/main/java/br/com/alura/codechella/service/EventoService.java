package br.com.alura.codechella.service;

import br.com.alura.codechella.dto.EventoDto;
import br.com.alura.codechella.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;


    public Flux<EventoDto> obterTodos() {
        return eventoRepository.findAll().map(EventoDto::toDto);

    }
}
