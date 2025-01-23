package br.com.alura.codechella.dto;

public record DadosTraducao(DadosResposta responseData) {

    public String getTexto() {
        return this.responseData.translatedText();
    }
}