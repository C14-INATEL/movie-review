# Dinâmica de Desenvolvimento — Movie Review

## 1. Divisão de Tarefas e Decisões Técnicas

As tarefas foram distribuídas de forma que cada membro ficasse responsável por uma parte coesa do projeto — combinando uma entrega de código com um stage do pipeline de CI/CD.

| Membro | Entrega de Código | Stage do Jenkins |
|--------|-------------------|------------------|
| Warley Ruivo | Model entities, `AvaliacaoRepository`, `AvaliacaoService` + testes Mockito | Stage 2 — Cobertura de Código (JaCoCo) |
| Vitória Bernardo | — | Stage 1 — Testes (Surefire) |
| Matheus Vieira | — | Stages 3 e 4 — SonarQube e Quality Gate |
| Matheus Henrique | — | Stage 5 — Build e empacotamento do JAR |
| Giuliana Batistella | — | Stage 6 — Publicar Release no GitHub |

O acompanhamento das tarefas foi feito via **Trello**, com cards movidos entre as colunas À fazer → Em andamento → Concluído à medida que cada entrega avançava.

## 2. Cadência e Reuniões

- **Reuniões síncronas a cada 15 dias** para alinhamento coletivo: revisão do que foi entregue, identificação de bloqueios e definição das próximas tarefas.
- **Trabalho assíncrono entre reuniões**: cada membro avançou de forma independente, abrindo Pull Requests e atualizando o Trello conforme concluía suas entregas.

Essa cadência permitiu que o grupo mantivesse ritmo sem depender de disponibilidade simultânea, resolvendo bloqueios nas reuniões quando o assíncrono não era suficiente.

## 3. Fluxo de Branches e Code Review

- Cada contribuição foi entregue via **Pull Request** dedicado, com descrição do que foi feito.
- Membros do grupo revisavam o PR antes do merge, garantindo que o código estava no pacote correto, os testes passavam e as convenções do projeto eram respeitadas.
- A branch `feature/pipeline-testes` foi usada de forma colaborativa para o Jenkinsfile: cada membro commitou seu stage em sequência, com o histórico de commits refletindo claramente quem adicionou o quê.

## 4. Conflitos e Bloqueios

### PR #9 — `feature/model-entities`

**Problema:** a branch divergia do main com um conflito estrutural grave. O diretório `movie-review/` era tratado como submódulo no main, mas como diretório comum na PR — tornando o merge automático impossível. Além disso, todos os arquivos Java da PR estavam vazios (`e69de29`) ou fora do pacote correto (`model/` em vez de `com.moviereview.model/`), e os testes usavam uma API incompatível com as classes já existentes no main (constructor com `id` em `Usuario`, constructor com `comentario` em `Avaliacao`).

**Solução:** a branch foi recriada a partir do `origin/main`, descartando toda a estrutura `movie-review/`. As contribuições únicas da PR foram identificadas, adaptadas à API real do projeto e recomitadas:
- `setNota()` com validação adicionado em `Avaliacao`
- `AvaliacaoValidacaoTest` e `UsuarioModelTest` criados com os testes adaptados
- Validação de nota ajustada de `< 0` para `< 1`, alinhando modelo e serviço

### PR `appmod/java-upgrade`

**Problema:** a branch continha apenas um commit que adicionava dois arquivos `AvaliacaoRepository.java` vazios — um na raiz do projeto (local errado) e um no pacote correto, porém sem nenhum conteúdo. O commit message prometia refatoração completa do `AvaliacaoService` e testes com Mockito, mas nada havia sido implementado.

**Solução:** implementação completa seguindo o padrão já estabelecido por `UsuarioRepository` e `UsuarioServiceMockTest`:
- Interface `AvaliacaoRepository` com `save`, `findByFilme` e `existsByUsuarioAndFilme`
- `AvaliacaoService` refatorado com segundo construtor injetando `AvaliacaoRepository`, delegando ao repositório quando presente e mantendo compatibilidade com os testes de integração existentes
- `AvaliacaoServiceMockTest` com 4 testes usando `@Mock` e `@InjectMocks`

## 5. Lições Aprendidas

- **Criar branches sempre a partir do main atualizado.** Branches criadas a partir de commits antigos acumulam divergências que tornam o merge manual e arriscado.
- **Verificar pacotes antes de commitar.** Arquivos fora de `com.moviereview.*` não são compilados junto com o restante do projeto e geram confusão no histórico.
- **Validações de negócio devem ser consistentes entre modelo e serviço.** A inconsistência entre `Avaliacao.setNota()` (que aceitava nota 0) e `AvaliacaoService` (que rejeitava nota 0) só foi percebida na revisão — testes de boundary no modelo teriam capturado isso mais cedo.
- **Reuniões periódicas ajudaram a desbloquear tarefas travadas.** Os conflitos de PR descritos acima foram identificados e resolvidos a partir de alinhamento em reunião, evitando que ficassem parados indefinidamente.
