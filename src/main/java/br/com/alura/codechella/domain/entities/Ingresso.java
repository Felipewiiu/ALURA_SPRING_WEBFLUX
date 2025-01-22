package br.com.alura.codechella.domain.entities;


import br.com.alura.codechella.domain.enums.TipoIngresso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ingressos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {
    @Id
    private Long id;

    private Long eventoId;

    private TipoIngresso tipo;

    private Double valor;

    private int total;


}