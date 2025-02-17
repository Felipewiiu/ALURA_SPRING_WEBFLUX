# Introdução à Programação Reativa

A programação reativa é um paradigma de programação que trata fluxos de dados e a propagação de mudanças. Em vez de
executar instruções sequenciais, ela foca em reagir a eventos ou mudanças de estado. De acordo com o Manifesto Reativo,
uma aplicação reativa deve ser:

- **Resiliente**
- **Elástica**
- **Responsiva**
- **Orientada à mensagem**

## Conceitos Fundamentais

### 1. Fluxos de Dados (Streams)

Sequências de eventos ou valores que são observados e manipulados ao longo do tempo, suportando diversos operadores para
se chegar a um resultado. Exemplos:

- Cliques do usuário
- Mensagens de uma fila
- Alterações em uma base de dados

### 2. Observáveis (Observables)

Fontes de dados que podem ser observadas. Eles emitem eventos que os observadores podem escutar e reagir.

### 3. Observadores (Observers)

Entidades que escutam eventos emitidos pelos observáveis e executam ações em resposta a esses eventos.

### 4. Operadores

Funções que permitem transformar, combinar e filtrar fluxos de dados. Exemplos:

- `map`
- `filter`
- `merge`
- `concat`

### 5. Backpressure

Forma de lidar com fluxos de dados que produzem eventos mais rapidamente do que podem ser consumidos. As técnicas de
backpressure ajudam a evitar sobrecarga e perda de dados.

A programação reativa é especialmente útil em aplicações que precisam lidar com muitos eventos ou que requerem alta
escalabilidade e desempenho, como:

- Sistemas de tempo real
- Aplicações web interativas
- Processamento de dados assíncrono

## Programação Reativa com Spring Framework

O **Spring Framework** oferece um conjunto de recursos para trabalhar com programação reativa, principalmente através do
projeto **Spring WebFlux**.

### Spring WebFlux

O **Spring WebFlux** é o módulo do Spring projetado para criar aplicações reativas. Ele oferece suporte a dois modelos
de programação:

1. **Baseado em Anotação (Annotation-based):**
    - Similar ao modelo tradicional do Spring MVC
    - Uso de anotações como `@Controller`, `@RestController` e `@RequestMapping`

2. **Baseado em Funções (Functional):**
    - Uso de handlers e routers
    - Endpoints definidos de forma programática

### Reactor

O **Reactor** é uma biblioteca reativa baseada no padrão **Reactive Streams**, que serve como base para o Spring
WebFlux. Ele fornece dois tipos principais de publicadores:

- **Mono:** Representa um único valor assíncrono ou vazio.
- **Flux:** Representa uma sequência de 0 a N valores assíncronos.

### Controladores Reativos

No Spring WebFlux, é possível definir controladores usando anotações, assim como no Spring MVC, mas retornando os
tipos `Mono` e `Flux`.

### WebClient

O **WebClient** é uma alternativa reativa ao `RestTemplate` e é usado para fazer chamadas HTTP assíncronas.

### Acesso a Dados Reativo

Para acesso reativo a bancos de dados, o Spring oferece suporte através dos seguintes projetos:

- **Spring Data R2DBC:** Para bancos de dados relacionais.
- **Spring Data MongoDB:** Para o MongoDB, proporcionando repositórios reativos.

### Suporte a SSE (Server-Sent Events) e WebSockets

O Spring WebFlux facilita a criação de aplicações que usam **SSE** e **WebSockets** para comunicação bidirecional e em
tempo real.

### Testes Reativos

O Spring fornece suporte para testar fluxos reativos com:

- **WebTestClient**
- **StepVerifier**

## Retornos de APIs usando webFlux

Quando usamos o WEBFLUX ele tem como retorno de respostas das sua requisições ( 0 ou 1 ) mesmo que ele não encontre o
recurso solicitado ele sempre lança o status 200. para tartar essa situação temos que usar o método ``switchIfEmpty()``
para tratar exceções

````java
 public Mono<EventoDto> obterPorId(Long id) {
    return eventoRepository.findById(id)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .map(EventoDto::toDto);
}
````

## SSE Server-Sent Event

Server-Sent Events (SSE) é uma tecnologia que permite que um servidor envie atualizações automáticas para um cliente via
uma única conexão HTTP. É útil para aplicações que precisam de atualizações contínuas do servidor, como notificações em
tempo real, feeds de dados ao vivo ou atualizações de estado de aplicativos.

### Funcionamento:

* Conexão unidirecional: Diferente do WebSocket, que é bidirecional, o SSE é unidirecional. O servidor pode enviar dados
  para o cliente, mas o cliente não pode enviar dados de volta pela mesma conexão.

* Conexão persistente: O cliente abre uma conexão HTTP com o servidor, que permanece aberta, permitindo que o servidor
  envie eventos sempre que novos dados estiverem disponíveis.

* Formato de dados: Os dados são enviados como texto simples, com eventos separados por duas novas linhas. Cada evento
  pode conter um ou mais campos de dados.

## SINK

Para configurar a propagação de mudança de estados da API, basta injetar na classe controller o :

````java
 private final Sinks.Many<EventoDto> eventosSink = Sinks.many().multicast().onBackpressureBuffer();
````

e depois configurar os end points para lidar com as mudanças com ``Flux.merge``. A baixo podemos verificar a
configuração completa:

````java

@GetMapping(value = "/categoria/{tipo}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<EventoDto> obterPorTipo(@PathVariable String tipo) {
    return Flux.merge(eventoService.obterPorTipo(tipo), eventosSink.asFlux())
            .delayElements(Duration.ofSeconds(4));
}
````

Configuração da observabilidade da mudança no método post:

````java

@PostMapping
public Mono<EventoDto> cadastrar(@RequestBody EventoDto eventoDto) {
    return eventoService.cadastrar(eventoDto).doOnSuccess(e -> eventosSink.tryEmitNext(eventoDto));
}
````

## WEBCLIENTE

O WebClient é uma classe fornecida pelo framework Spring WebFlux no ecossistema do Spring Framework. Ele é usado para
realizar requisições HTTP de forma reativa e não-bloqueante. É uma alternativa moderna ao RestTemplate, que funciona de
forma bloqueante e está sendo gradualmente desincentivado.

## Testes com o WEB FLUX

1. WebTestClient:

Ferramenta principal para testar aplicações Spring WebFlux. Simula chamadas HTTP para endpoints reativos e pode
verificar respostas de forma assíncrona. Pode ser utilizado em testes unitários e de integração.

````java

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetEndpoint() {
        webTestClient.get()
                .uri("/api/resource")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ResourceDto.class)
                .hasSize(10)
                .contains(expectedResource);
    }
}
````

2. StepVerifier:

Verifica o comportamento de Mono e Flux ao longo do tempo, permitindo que você garanta o resultado do fluxo de dados
emitido. É recomendado para testes unitários de serviços ou componentes que retornam Mono ou Flux.

````java
@Test
void testServiceMethod() {
    Flux<String> flux = service.getDataFlux();

    StepVerifier.create(flux)
                .expectNext("data1", "data2")
                .expectComplete()
                .verify();
}
````

3. TestPublisher:

````java
@Test
void testWithTestPublisher() {
    TestPublisher<String> publisher = TestPublisher.create();

    Flux<String> flux = publisher.flux();

    StepVerifier.create(flux)
                .then(() -> publisher.emit("data1", "data2"))
                .expectNext("data1", "data2")
                .expectComplete()
                .verify();
}
````