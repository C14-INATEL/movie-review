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

Execute o aplicativo:

```bash
mvn exec:java -Dexec.mainClass="com.moviereview.Menu"
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

---

## Uso de IA

Conforme as diretrizes de avaliação da NP2, declaramos o uso transparente de assistentes de IA durante o ciclo de desenvolvimento do projeto para aprimoramento do processo de engenharia de software.

### Modelos Utilizados

- **Gemini** (Google) — geração de código, sugestão de testes e documentação
- **Claude Code** (Anthropic) — suporte à configuração do pipeline Jenkins, debug de scripts shell e refatoração de Jenkinsfile

### Aplicações Práticas

- **Refatoração de Código:** Auxílio na reestruturação do menu do terminal (`Menu.java`) com estruturas de controle limpas e tratamento robusto de entradas inválidas.
- **Engenharia de Testes:** Apoio no desenho dos casos de teste com Mockito para simular repositórios e clientes externos.
- **Configuração de CI/CD:** Suporte na depuração de scripts shell no Jenkinsfile, especificamente na extração de IDs da API do GitHub e no comportamento do dash com `set -e`.
- **Documentação:** Apoio na estruturação das histórias de usuário no formato Given/When/Then.

### Exemplos de Prompts e Resultados

| Prompt | O que foi feito com o resultado |
|--------|--------------------------------|
| "Como estruturar uma história de usuário que cubra o cadastro de filmes e o tratamento de erros do menu numérico no padrão de Engenharia de Software?" | Estrutura sugerida aceita; critérios de aceitação adaptados para o domínio real do projeto (filmes, notas 1-5, regras de duplicidade). |
| "Como criar testes unitários utilizando Mockito para validar chamadas a um repositório em Java?" | Código gerado revisado e adaptado: nomes de variáveis ajustados para o domínio (`usuarioService`, `repository`), `@DisplayName` adicionado manualmente para clareza. |
| "Por que o script shell no Jenkinsfile falha ao extrair o RELEASE_ID com `grep`?" | Diagnóstico correto (comportamento do `set -e` em dash ao executar `$(grep...)`); solução aplicada com ajuste de padrão e adição de `|| true`. |

### O Que NÃO Foi Feito por IA

- A definição das regras de negócio (`AvaliacaoService`: validação 1–5, bloqueio de duplicidade, cálculo de média) foi escrita manualmente, com os testes de boundary desenvolvidos pelo grupo antes de qualquer consulta a IA.
- A arquitetura de camadas (model/service/repository/client) e a decisão pelo padrão dual-constructor para testabilidade foram definidas pelo grupo.
- A resolução dos conflitos de merge (PR #9 e appmod/java-upgrade) e a depuração dos problemas estruturais de pacote foram feitas manualmente, sem assistência de IA.

### Dinâmica de Uso

A IA atuou como co-piloto consultivo. Nenhuma lógica foi integrada ao repositório sem revisão manual completa, refatoração de nomenclatura para o domínio do projeto e validação via execução da suíte de testes (`mvn test`).

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
