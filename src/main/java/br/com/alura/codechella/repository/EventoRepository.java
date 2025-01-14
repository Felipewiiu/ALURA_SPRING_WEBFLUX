package br.com.alura.codechella.repository;

import br.com.alura.codechella.domain.entities.Evento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends ReactiveCrudRepository<Evento, Long> {
}
