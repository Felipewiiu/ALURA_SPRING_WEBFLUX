package br.com.alura.codechella.domain.entities;

import br.com.alura.codechella.domain.enums.TipoEvento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("eventos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Evento {

    @Id
    private Long id;

    private TipoEvento tipo;

    private String nome;

    private LocalDate data;

    private String descricao;
}
