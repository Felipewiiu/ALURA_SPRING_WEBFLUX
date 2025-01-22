package br.com.alura.codechella.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("vendas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
    @Id
    private Long id;
    private Long ingressoId;
    private int total;


}