## Histórias de Usuários

Esta seção apresenta as histórias de usuário desenvolvidas ao longo do projeto, demonstrando a aplicação dos conceitos de Engenharia de Software e a rastreabilidade entre requisitos, implementação e testes automatizados.

### US04 – Cadastro de Filmes no Catálogo

- **Como** administrador do sistema Movie Review,

- **Eu quero** cadastrar novos filmes no catálogo,

- **Para que** eles possam ser consultados e avaliados pelos usuários da plataforma.

#### Critérios de Aceitação

##### Cenário 1 – Cadastro realizado com sucesso

- **Given** que o administrador acessou a funcionalidade de cadastro de filmes

- **When** ele informa um título inédito, diretor e ano de lançamento válidos

- **Then** o sistema deve registrar o filme e exibir uma mensagem de confirmação.

##### Cenário 2 – Bloqueio de filmes duplicados

- **Given** que já existe um filme cadastrado com determinado título

- **When** o administrador tenta cadastrar novamente o mesmo filme

- **Then** o sistema deve impedir a duplicidade e informar que o filme já está cadastrado.

##### Cenário 3 – Entrada inválida durante o cadastro

- **Given** que o menu principal está aguardando uma opção

- **When** o usuário informa texto, caracteres inválidos ou uma entrada incompatível

- **Then** o sistema deve tratar a exceção e permitir que o usuário tente novamente sem encerrar a aplicação.

#### Prioridade

- Alta

#### Status

- Entregue

#### Rastreabilidade

**Issue:** Não formalizada no GitHub Issues.

**Pull Request:** #22

**Commits relacionados:**

* `fix(menu): Ajuste para não crashar o menu`
* `finish menu and testes`

**Classes envolvidas:**

* `Menu.java`
* `FilmeService.java`

**Testes Automatizados:**

* `MenuTest.java`
* `FilmeServiceTest.java`

#### Contribuição da Integrante

Responsável pela implementação das melhorias no fluxo de cadastro de filmes, estabilização do menu principal através do tratamento de entradas inválidas e adequação dos testes automatizados relacionados às funcionalidades desenvolvidas.