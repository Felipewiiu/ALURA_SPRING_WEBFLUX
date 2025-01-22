package br.com.alura.codechella.repository;

import br.com.alura.codechella.domain.entities.Ingresso;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IngressoRepository extends ReactiveCrudRepository<Ingresso, Long> {
}