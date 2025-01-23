package br.com.alura.codechella.translate;

import br.com.alura.codechella.dto.DadosTraducao;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class TraducaoDeTextos {

    public static Mono<String> obterTraducaoMyMemory(String texto, String idioma) {
        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=pt-br|" + idioma;

        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .build();

        return  webClient.get()
                .retrieve()
                .bodyToMono(DadosTraducao.class)
                .map(DadosTraducao::getTexto);

    }
}
