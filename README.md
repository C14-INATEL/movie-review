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

- Java 17
- Maven
- JUnit 5
- Mockito
- JaCoCo
- SonarCloud
- Jenkins

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

O projeto esta em fase de refatoracao e consolidacao das branches. A prioridade
atual e alinhar os contratos entre servicos e testes, padronizar pacotes e
garantir que a `main` compile e rode a suite de testes com sucesso.

## Proximos Passos

- finalizar o fluxo completo do menu;
- integrar cadastro e avaliacao de filmes pela interface de terminal;
- consolidar as regras de validacao das entidades;
- padronizar a comparacao entre usuarios e filmes;
- fortalecer os testes de regressao;
- concluir a configuracao de CI/CD.

## Licenca

Projeto desenvolvido para fins academicos.
