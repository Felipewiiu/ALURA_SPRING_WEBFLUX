package br.com.alura.codechella.dto;

import br.com.alura.codechella.domain.entities.Evento;
import br.com.alura.codechella.domain.enums.TipoEvento;

import java.time.LocalDate;

public record EventoDto(
        Long id,

        TipoEvento evento,

        String nome,

        LocalDate data,

        String descricao
) {

    public static EventoDto toDto(Evento evento) {
        return new EventoDto(
                evento.getId(),
                evento.getTipo(),
                evento.getNome(),
                evento.getData(),
                evento.getDescricao()
        );
    }
}
