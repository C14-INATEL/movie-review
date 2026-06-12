## Histórias de Usuários

Esta seção apresenta as histórias de usuário desenvolvidas ao longo do projeto, demonstrando a aplicação dos conceitos de Engenharia de Software e a rastreabilidade entre requisitos, implementação e testes automatizados.

### US03 – Registro de Avaliação de Filmes

- **Como** usuário cadastrado no sistema Movie Review,

- **Eu quero** registrar uma avaliação com nota para um filme,

- **Para que** minha opinião fique associada ao filme e contribua para o histórico de avaliações da plataforma.

#### Critérios de Aceitação

##### Cenário 1 – Avaliação registrada com sucesso

- **Given** que o usuário está cadastrado e o filme existe no catálogo

- **When** ele submete uma avaliação com nota entre 1 e 5

- **Then** o sistema deve persistir a avaliação associando-a ao usuário e ao filme correspondente.

##### Cenário 2 – Nota fora do intervalo permitido

- **Given** que o usuário tenta registrar uma avaliação com nota 0 ou nota 6

- **When** a avaliação é submetida

- **Then** o sistema deve lançar `IllegalArgumentException` com a mensagem "Nota deve ser entre 1 e 5".

##### Cenário 3 – Avaliação duplicada

- **Given** que o usuário já registrou uma avaliação para determinado filme

- **When** ele tenta submeter uma segunda avaliação para o mesmo filme

- **Then** o sistema deve lançar `IllegalStateException` impedindo a duplicidade.

##### Cenário 4 – Usuário não encontrado

- **Given** que o usuário informado não está cadastrado no sistema

- **When** ele tenta submeter uma avaliação

- **Then** o sistema deve lançar `IllegalArgumentException` com a mensagem "Usuário não encontrado."

#### Prioridade

- Alta

#### Status

- Entregue

#### Rastreabilidade

**Issue:** Não formalizada no GitHub Issues.

**Pull Request:** #7

**Commits relacionados:**

* `feat: adiciona modelo de avaliação com ligação entre usuário e filme com testes unitários`
* `chore: mock tests`

**Classes envolvidas:**

* `Avaliacao.java`
* `AvaliacaoService.java`
* `Filme.java`
* `Usuario.java`

**Testes Automatizados:**

* `AvaliacaoServiceTest.java`

#### Contribuição da Integrante

Responsável pela criação do modelo de avaliação com vínculo entre usuário e filme, implementação das regras de negócio no `AvaliacaoService` (validação de nota e bloqueio de duplicidade), adição de suporte a mocks nos serviços e expansão da cobertura de testes unitários com Mockito. Também contribuiu com a etapa de publicação de release no pipeline de CI/CD via `Jenkinsfile`.
