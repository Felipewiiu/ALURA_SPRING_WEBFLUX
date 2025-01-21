package br.com.alura.codechella.repository;

import br.com.alura.codechella.domain.entities.Evento;
import br.com.alura.codechella.domain.enums.TipoEvento;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EventoRepository extends ReactiveCrudRepository<Evento, Long> {
    Flux<Evento> findByTipo(TipoEvento tipoEvento);

}
