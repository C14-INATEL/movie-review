# Movie Review

Sistema simples de avaliacao de filmes desenvolvido em Java, com foco em
organizacao de servicos, modelos de dominio, testes unitarios e trabalho
colaborativo por branches.

O projeto simula uma aplicacao de catalogo de filmes na qual usuarios podem ser
cadastrados, filmes podem ser consultados/cadastrados e avaliacoes podem ser
registradas para compor medias e rankings.

## Objetivo

O Movie Review foi criado como um projeto academico do grupo para praticar:

- modelagem de entidades em Java;
- separacao entre camada de modelo, servico, repositorio e cliente externo;
- testes unitarios com JUnit 5;
- uso de mocks com Mockito;
- controle de versao com branches e Pull Requests;
- preparacao para integracao continua com Jenkins, JaCoCo e Sonar.

## Funcionalidades

Atualmente o projeto contempla:

- cadastro e listagem de usuarios;
- validacao de email duplicado;
- cadastro e consulta de filmes em memoria;
- busca de filmes por uma API externa simulada por interface;
- registro de avaliacoes de filmes;
- validacao de notas;
- calculo de media de avaliacoes por filme;
- testes unitarios para modelos e servicos;
- testes com mocks para dependencias externas e repositorios.

Algumas funcionalidades do menu ainda estao em evolucao, como o fluxo completo
de avaliacao pelo terminal e a visualizacao de ranking.

## Tecnologias

- Java 21
- Maven
- JUnit 5
- Mockito
- JaCoCo
- SonarCloud
- Jenkins (CI/CD)

## Estrutura

```text
movie-review/
├── backend/
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/moviereview/
│       │   ├── Menu.java
│       │   ├── client/
│       │   ├── model/
│       │   ├── repository/
│       │   └── service/
│       └── test/java/com/moviereview/
│           ├── fakeDatabase/
│           ├── model/
│           └── service/
├── README.md
└── .gitignore
```

## Camadas do Projeto

### Model

Representa as entidades centrais do sistema:

- `Usuario`: dados de nome, email e senha;
- `Filme`: titulo, diretor e ano de lancamento;
- `Avaliacao`: vinculo entre usuario, filme e nota.

### Service

Contem as regras de negocio principais:

- `UsuarioService`: cadastro, validacao de email e listagem de usuarios;
- `AvaliacaoService`: registro de avaliacoes, validacao de nota e media;
- `BuscaFilmeApiService`: integracao com cliente externo de busca de filmes.

### Repository

Define contratos para persistencia ou simulacao de persistencia:

- `UsuarioRepository`;
- `AvaliacaoRepository`, nas branches mais recentes.

### Client

Define a interface de comunicacao com uma API externa de filmes:

- `FilmeApiClient`.

## Como Executar

Entre na pasta do backend:

```bash
cd backend
```

Compile o projeto:

```bash
mvn clean compile
```

Execute os testes:

```bash
mvn test
```

Execute verificacoes com relatorio de cobertura, quando configurado:

```bash
mvn verify
```

## Testes

O projeto utiliza JUnit 5 para testes unitarios e Mockito para simular
dependencias externas.

Exemplos de cenarios cobertos:

- cadastro de usuario com sucesso;
- bloqueio de email duplicado;
- listagem defensiva de usuarios;
- registro de avaliacao;
- bloqueio de notas invalidas;
- calculo de media por filme;
- busca de filme via cliente externo mockado;
- verificacao de chamadas a repositorios mockados.

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

## Uso de IA

Conforme as diretrizes de avaliação da NP2, declaramos o uso transparente de assistentes de IA durante o ciclo de desenvolvimento do projeto para aprimoramento do processo de engenharia de software.

Modelos Utilizados:
- Gemini (Google).

Aplicações Práticas:
- Refatoração de Código: Auxílio na reestruturação do menu do terminal (Menu.java) utilizando estruturas de controle limpas e tratamento robusto de exceções de entrada de dados.
- Engenharia de Testes: Apoio no desenho e escrita dos casos de teste baseados em dublês de teste (Mocks) utilizando JUnit 5 e Mockito para simular falhas e regras de persistência.

Exemplos de Prompts Realizados:
- "Como estruturar uma história de usuário que cubra o cadastro de filmes e o tratamento de erros do menu numérico no padrão de Engenharia de Software?"
- "Qual comando do Git utilizo para listar exclusivamente os meus commits condensados em apenas uma linha para fins de relatório?"
- "Como criar testes unitários utilizando Mockito para validar chamadas a um repositório em Java?"

Dinâmica de Uso:
- A IA atuou como uma ferramenta de co-piloto consultivo e revisão por pares estendida. Nenhuma lógica foi integrada ao repositório sem que houvesse a completa revisão manual, refatoração de nomes para o escopo do domínio e validação local via execução completa de testes automatizados

## Fluxo de Trabalho com Branches

Este projeto nao deve receber commits diretamente na `main`.

Fluxo recomendado:

```bash
git fetch origin
git checkout nome-da-branch
git pull origin nome-da-branch
```

Depois de aplicar a alteracao:

```bash
cd backend
mvn test
```

Se os testes passarem:

```bash
git add .
git commit -m "descricao objetiva da correcao"
git push origin nome-da-branch
```

Por fim, abrir um Pull Request para a `main`.

## Boas Praticas do Projeto

- manter cada correcao na branch responsavel;
- evitar alterar arquivos fora do escopo da tarefa;
- sempre rodar os testes antes de abrir Pull Request;
- revisar conflitos antes do merge;
- manter mensagens de commit claras;
- preferir testes unitarios pequenos e objetivos;
- usar mocks apenas quando houver dependencia externa ou contrato de repositorio.

## Status Atual

O projeto concluiu a fase de estabilização estrutural. A suite principal de testes automatizados integrada com a pipeline de CI/CD está operacional, com 100% de sucesso nas validações das regras de negócio mapeadas.

## Licenca

Projeto desenvolvido para fins academicos.
