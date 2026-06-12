# Histórias de Usuário — Matheus Vieira

## US — Busca de Filme por API Externa

- **Como** usuário do sistema Movie Review,

- **Eu quero** buscar um filme pelo título consultando uma fonte externa,

- **Para que** eu possa encontrar filmes que ainda não foram cadastrados manualmente no catálogo local.

### Critérios de Aceitação

#### Cenário 1 – Busca retorna o filme com sucesso

- **Given** que o título informado corresponde a um filme disponível na API externa

- **When** o sistema realiza a consulta através do `BuscaFilmeApiService`

- **Then** o sistema deve retornar o objeto `Filme` com título, diretor e ano de lançamento preenchidos

#### Cenário 2 – API externa retorna falha

- **Given** que a API externa está indisponível ou retorna um erro

- **When** o sistema tenta buscar o filme pelo título

- **Then** o sistema deve propagar a exceção lançada pelo cliente externo sem suprimi-la

### Prioridade

Média

### Status

Entregue

### Rastreabilidade

**Pull Request:** #10

**Commits relacionados:**

- `feat: adiciona mock de API externa de filmes com testes unitários`

**Classes envolvidas:**

- `FilmeApiClient.java` — interface de contrato com a API externa
- `BuscaFilmeApiService.java` — serviço que delega a busca ao cliente
- `BuscaFilmeApiServiceTest.java`

**Testes Automatizados:**

- `BuscaFilmeApiServiceTest#deveBuscarFilmePorTituloNaApiExterna`
- `BuscaFilmeApiServiceTest#deveLancarExcecaoQuandoApiRetornaErro`

### Contribuição

Responsável pela definição do contrato `FilmeApiClient` como interface desacoplada da implementação, implementação do `BuscaFilmeApiService` com injeção de dependência via construtor, e cobertura completa com testes usando Mockito para simular respostas da API externa e cenários de falha.
