package br.com.alura.codechella.repository;

import br.com.alura.codechella.domain.entities.Venda;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VendaRepository extends ReactiveCrudRepository<Venda, Long> {
}
