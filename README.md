
# Introdução à Programação Reativa

A programação reativa é um paradigma de programação que trata fluxos de dados e a propagação de mudanças. Em vez de executar instruções sequenciais, ela foca em reagir a eventos ou mudanças de estado. De acordo com o Manifesto Reativo, uma aplicação reativa deve ser:

- **Resiliente**
- **Elástica**
- **Responsiva**
- **Orientada à mensagem**

## Conceitos Fundamentais

### 1. Fluxos de Dados (Streams)
Sequências de eventos ou valores que são observados e manipulados ao longo do tempo, suportando diversos operadores para se chegar a um resultado. Exemplos:
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
Forma de lidar com fluxos de dados que produzem eventos mais rapidamente do que podem ser consumidos. As técnicas de backpressure ajudam a evitar sobrecarga e perda de dados.

A programação reativa é especialmente útil em aplicações que precisam lidar com muitos eventos ou que requerem alta escalabilidade e desempenho, como:
- Sistemas de tempo real
- Aplicações web interativas
- Processamento de dados assíncrono

## Programação Reativa com Spring Framework

O **Spring Framework** oferece um conjunto de recursos para trabalhar com programação reativa, principalmente através do projeto **Spring WebFlux**.

### Spring WebFlux
O **Spring WebFlux** é o módulo do Spring projetado para criar aplicações reativas. Ele oferece suporte a dois modelos de programação:

1. **Baseado em Anotação (Annotation-based):**
    - Similar ao modelo tradicional do Spring MVC
    - Uso de anotações como `@Controller`, `@RestController` e `@RequestMapping`

2. **Baseado em Funções (Functional):**
    - Uso de handlers e routers
    - Endpoints definidos de forma programática

### Reactor
O **Reactor** é uma biblioteca reativa baseada no padrão **Reactive Streams**, que serve como base para o Spring WebFlux. Ele fornece dois tipos principais de publicadores:

- **Mono:** Representa um único valor assíncrono ou vazio.
- **Flux:** Representa uma sequência de 0 a N valores assíncronos.

### Controladores Reativos
No Spring WebFlux, é possível definir controladores usando anotações, assim como no Spring MVC, mas retornando os tipos `Mono` e `Flux`.

### WebClient
O **WebClient** é uma alternativa reativa ao `RestTemplate` e é usado para fazer chamadas HTTP assíncronas.

### Acesso a Dados Reativo
Para acesso reativo a bancos de dados, o Spring oferece suporte através dos seguintes projetos:

- **Spring Data R2DBC:** Para bancos de dados relacionais.
- **Spring Data MongoDB:** Para o MongoDB, proporcionando repositórios reativos.

### Suporte a SSE (Server-Sent Events) e WebSockets
O Spring WebFlux facilita a criação de aplicações que usam **SSE** e **WebSockets** para comunicação bidirecional e em tempo real.

### Testes Reativos
O Spring fornece suporte para testar fluxos reativos com:
- **WebTestClient**
- **StepVerifier**

## Conclusão
A programação reativa é uma abordagem poderosa para construir aplicações responsivas, escaláveis e orientadas a eventos. Com o Spring WebFlux e as ferramentas reativas do ecossistema Spring, é possível criar soluções modernas e eficientes para os desafios do desenvolvimento de software. Vamos explorar esses recursos ao longo do curso!

