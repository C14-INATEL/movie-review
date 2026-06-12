# Metodologia de Desenvolvimento — Movie Review

## Metodologia Adotada

O grupo adotou um **Kanban leve** com reuniões periódicas a cada 15 dias. O trabalho assíncrono é conduzido via Pull Requests no GitHub, onde cada membro entrega sua contribuição de forma independente e o grupo revisa antes do merge.

## Ferramenta de Gestão

**Trello** — acompanhamento de tarefas e progresso do projeto. Cada card representa uma entrega (feature, stage do pipeline, documentação) e é movido entre as colunas À fazer → Em andamento → Concluído.

## Integrantes e Responsabilidades

| Membro | Responsabilidades |
|--------|-------------------|
| **Warley Ruivo** | Model entities (`Usuario`, `Filme`, `Avaliacao`), `AvaliacaoRepository`, `AvaliacaoService` com testes mock, Stage 2 do Jenkins (cobertura JaCoCo), metodologia e dinâmica |
| **Vitória Bernardo** | Stage 1 do Jenkins (execução de testes com Surefire) |
| **Matheus Vieira** | Stages 3 e 4 do Jenkins (SonarQube Analysis e Quality Gate) |
| **Matheus Henrique** | Stage 5 do Jenkins (Build e empacotamento do JAR) |
| **Giuliana Batistella** | Stage 6 do Jenkins (Publicar Release no GitHub) |

## Cadência

- **Reuniões síncronas:** a cada 15 dias para alinhamento, revisão de entregas e definição das próximas tarefas
- **Trabalho assíncrono:** cada membro abre um Pull Request com sua contribuição; o merge ocorre após revisão do grupo

## Ferramentas Utilizadas

| Ferramenta | Finalidade |
|------------|------------|
| GitHub | Versionamento, Pull Requests e Releases |
| Jenkins | Pipeline de CI/CD (testes, cobertura, análise, build, release) |
| Maven | Build, testes e empacotamento do backend Java |
| Trello | Gestão de tarefas e acompanhamento do progresso |
| SonarQube / SonarCloud | Análise estática de código e quality gate |
| JaCoCo | Relatório de cobertura de testes |

## Métricas de Acompanhamento

- **PRs mergeadas por membro** — indica participação efetiva no repositório
- **Stages do pipeline entregues por membro** — rastreia a contribuição de cada integrante no CI/CD
- **Cobertura de testes** — monitorada via JaCoCo e publicada pelo Jenkins a cada build
