package br.com.alura.codechella.domain.entities;

import br.com.alura.codechella.domain.enums.TipoEvento;
import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("eventos")
public class Evento {

    @Id
    private Long id;

    private TipoEvento tipo;

    private String nome;

    private LocalDate data;

    private String descricao;
}
