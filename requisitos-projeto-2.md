# Requisitos de Projeto — C14 (NP2): Guia de Avaliação para Claude Code

> **Propósito deste arquivo:** servir como prompt/contexto de referência para o **Claude Code** avaliar um repositório de projeto da disciplina **C14 — Engenharia de Software (INATEL)**, fase **NP2**. Coloque este arquivo na raiz (ou em `docs/`) do repositório a ser avaliado e peça ao Claude Code para seguir as instruções da seção *"Como conduzir a avaliação"*.

---

## 0. Instruções para o agente (Claude Code)

Você é um avaliador técnico rigoroso e justo. Sua tarefa é **avaliar o repositório atual** contra os 14 critérios da NP2 de C14, atribuir uma nota de **0 a 100 por critério** e justificar **cada nota com evidência concreta** (caminho de arquivo, hash de commit, número de PR/issue, linha de log de pipeline).

Princípios obrigatórios:

- **Evidência acima de afirmação.** Nunca dê nota com base em "parece que sim". Aponte o artefato. Se não houver evidência, a nota cai e você diz exatamente o que faltou.
- **Não invente.** Se não conseguir acessar um dado (ex.: histórico de PRs porque está só com o working tree local), declare a limitação e avalie o que for possível.
- **Distribua a leitura.** Inspecione de fato: `git log`, `git shortlog -sne`, árvore de diretórios, arquivos de CI, suíte de testes, README e qualquer doc de histórias/metodologia.
- **Seja específico nos gaps.** Cada nota abaixo de 100 deve vir acompanhada de "o que faria subir".

### Comandos úteis para coletar evidência

```bash
# Panorama de contribuições por autor
git shortlog -sne --all

# Histórico com datas e mensagens
git log --pretty=format:'%h | %an | %ad | %s' --date=short --all | head -100

# Distribuição de commits por autor ao longo do tempo
git log --pretty=format:'%an' --all | sort | uniq -c | sort -rn

# Branches e merges
git branch -a
git log --merges --oneline --all

# Arquivos de CI/CD presentes (procurando alternativas ao GitHub Actions)
ls -la .gitlab-ci.yml Jenkinsfile .circleci/ .drone.yml azure-pipelines.yml bitbucket-pipelines.yml .travis.yml 2>/dev/null

# Gerenciador de dependências e lockfile
ls -la pom.xml package.json package-lock.json yarn.lock requirements.txt poetry.lock Pipfile.lock build.gradle 2>/dev/null

# Encontrar testes
find . -type d -name "test*" -o -type d -name "*test*" 2>/dev/null | grep -v node_modules
```

---

## 1. Visão geral do projeto avaliado

A NP2 é a **continuação** do projeto de C14 do semestre. Mantêm-se todos os requisitos originais e somam-se três novos blocos obrigatórios (histórias de usuário, metodologia e dinâmica), além de defesa em formato **Q&A** e seção transparente de **uso de IA**.

Tipos de aplicação permitidos: **Web / Mobile / PWA / Desktop**. Linguagem e frameworks livres.

---

## 2. Requisitos mantidos (base da fase anterior)

| Requisito | O que verificar no repositório |
|---|---|
| **Tecnologia** | App Web/Mobile/PWA/Desktop com escopo e tema bem definidos. |
| **Gerenciador de dependências** | Maven / npm / pip / Gradle (ou equivalente) **com arquivo de lock versionado** (`package-lock.json`, `poetry.lock`, etc.). |
| **App funcional + boas práticas** | Código legível, organizado, aplicando conceitos vistos em sala. |
| **Testes automatizados** | Testes unitários relevantes ao domínio; framework adequado ao stack; **relatórios via CI/CD**. |
| **Versionamento e CI/CD** | Repositório no time da matéria; contribuições significativas de todos; **GitHub Actions NÃO é permitido** — usar outra ferramenta; **≥ 1 job por integrante**, comitado pelo próprio. |
| **Revisão de código** | Processo real de **pull requests** com discussão entre membros; testes/jobs/endpoints relevantes ao domínio. |
| **Equipe e entrega** | Grupos de **4 a 6** integrantes; **README completo** (instalação, execução, uso, funcionalidades); todos participam de todas as entregas; defesa de até **20 min**. |

> ⚠️ **Atenção CI/CD:** se a única automação encontrada for `.github/workflows/` (GitHub Actions), isso **viola** a regra. Procure Jenkins, GitLab CI, CircleCI, Drone, Azure Pipelines, etc.

---

## 3. Novos requisitos da NP2 (todos obrigatórios)

### 3.1 — Histórias de Usuário
- Formato: **"Como `<perfil>`, eu quero `<ação>` para que `<benefício>`."**
- **Mínimo de 5** histórias bem escritas, cobrindo as funcionalidades centrais.
- Cada história com **critérios de aceitação claros** (ex.: Given/When/Then).
- Indicar **prioridade** (alta/média/baixa) e **status final** (entregue / parcial / descartada).
- **Rastreabilidade:** história → issue/PR → teste automatizado.

### 3.2 — Metodologia de Desenvolvimento
- Qual metodologia (Scrum, Kanban, XP, híbrida...) e **por quê** — com evidências, não só "usamos ágil".
- **Papéis** no grupo (PO, facilitador, dev, QA...) — quem fez o quê.
- **Cadência:** duração de sprints/ciclos, reuniões, ferramentas (Jira, Trello, GitHub Projects, Notion...).
- **DoD** (definição de pronto) e **DoR** (definição de preparado), se aplicável.
- **Métricas simples:** issues fechadas por sprint, burndown, lead time, etc.

### 3.3 — Dinâmica de Desenvolvimento
- Divisão de tarefas e como as **decisões técnicas** foram tomadas.
- **Fluxo de branches**, padrão de commits e processo de code review.
- Conflitos, bloqueios e como o grupo se reorganizou.
- **Lições aprendidas** — o que fariam diferente.

---

## 4. Uso transparente de IA (seção obrigatória no README)

Verificar se o README tem uma seção **"Uso de IA"** com, no mínimo:

- Modelos utilizados (Claude, ChatGPT/GPT-4, Gemini, Copilot, Cursor...).
- Para quê foram usados (código, refatoração, testes, documentação, design, debugging, brainstorming...).
- **Pelo menos 3 exemplos reais de prompts** e o que foi aceito/ajustado/descartado.
- Dinâmica de uso (individual, pair programming, revisão de PRs, geração de testes...).
- **O que NÃO foi feito por IA** — partes desenvolvidas "à mão".

> A avaliação **não pune o uso de IA** — pune a falta de honestidade e a ausência de entendimento do que foi gerado.

---

## 5. Critérios de avaliação (0–100 cada)

Para **cada** critério: atribua nota, cite **evidência concreta** e indique **o que faria subir a nota**.

| # | Critério | O que é avaliado | Sinais de evidência forte |
|---|---|---|---|
| 1 | **Aplicação funcional** | App rodando, escopo claro, boas práticas, código legível. | App executa conforme README; arquitetura coerente. |
| 2 | **Gerenciamento de dependências** | Ferramenta adequada ao stack, build reproduzível, **lock versionado**. | Lockfile presente e atualizado; build limpo do zero. |
| 3 | **Testes unitários relevantes** | Cobertura útil, testes ligados ao domínio, sem "testes de soma". | Testes que exercem regras de negócio reais, não `assert(true)`. |
| 4 | **CI/CD (sem GitHub Actions)** | Pipeline funcional (build + testes), **≥ 1 job por integrante** comitado pelo próprio. | Jenkinsfile/`.gitlab-ci.yml` com jobs nomeados e autoria correta. |
| 5 | **Versionamento e contribuições** | Commits significativos de todos, histórico limpo, branches organizadas. | `git shortlog` equilibrado; mensagens descritivas. |
| 6 | **Revisão de código (PRs)** | Pull requests reais, com revisão e discussão entre membros. | PRs com comentários, aprovações e merges. |
| 7 | **README completo** | Instalação, execução, uso, funcionalidades **e seção de Uso de IA**. | README cobre os 5 itens + IA. |
| 8 | **Histórias de usuário** | Mínimo 5, com critérios de aceitação **e rastreabilidade**. | Histórias → issue/PR → teste mapeados. |
| 9 | **Metodologia de desenvolvimento** | Metodologia justificada, papéis, cadência, ferramentas, métricas. | Evidências (boards, sprints) e não só texto genérico. |
| 10 | **Dinâmica de desenvolvimento** | Relato honesto da dinâmica, lições aprendidas, decisões. | Narrativa concreta com conflitos e resoluções reais. |
| 11 | **Refactoring** | Quais refactorings foram aplicados e por quê — com evidência. | Commits/PRs de refatoração com motivação clara. |
| 12 | **Uso transparente de IA** | Modelos, prompts, respostas aceitas/descartadas, dinâmica. | ≥ 3 prompts reais + o que foi feito à mão. |
| 13 | **Defesa Q&A** | Domínio do projeto pelo grupo todo, respostas concretas. | *(avaliado na apresentação — verificar prontidão do repo para navegação ao vivo)* |
| 14 | **Engenharia de Software geral** | Coerência entre requisitos, código, testes e pipeline. Visão de sistema. | Tudo "conversa": requisito → código → teste → pipeline. |

---

## 6. Critérios eliminatórios

A entrega pode ser **zerada ou drasticamente reduzida** se:

- Não comparecer no dia da defesa.
- Commits claramente **irrelevantes** ao longo do semestre.
- Cópias de código gerado por IA **sem explicação ou entendimento**.
- Respostas **totalmente erradas** sobre conteúdo de Engenharia de Software do semestre.
- **Discrepância clara de contribuição** entre integrantes.

> Ao avaliar, sinalize explicitamente se algum eliminatório foi acionado (especialmente **commits irrelevantes** e **discrepância de contribuição**, que são detectáveis no `git log`/`git shortlog`).

---

## 7. Como conduzir a avaliação (passo a passo para o Claude Code)

1. **Mapeie o repositório.** Liste a árvore de diretórios e identifique stack, ferramenta de dependências e localização dos testes.
2. **Audite o Git.** Rode os comandos da seção 0 para medir contribuição por autor, qualidade das mensagens e organização de branches.
3. **Verifique CI/CD.** Confirme que **não é** GitHub Actions e conte os jobs por integrante (verificando a autoria de quem comitou cada job).
4. **Rode/analise os testes.** Avalie se são relevantes ao domínio (não triviais) e se geram relatório no pipeline.
5. **Leia o README.** Cheque os 5 itens obrigatórios + seção de Uso de IA (≥ 3 prompts).
6. **Cheque os 3 blocos novos.** Histórias de usuário (≥ 5, com rastreabilidade), metodologia (com evidência) e dinâmica.
7. **Acione eliminatórios**, se houver.
8. **Produza o relatório** no formato da seção 8.

---

## 8. Formato de saída esperado

```markdown
# Avaliação — Projeto C14 NP2: <nome do projeto>

## Resumo executivo
- Nota global estimada: <0–100>
- Eliminatórios acionados: <nenhum / lista>
- 3 maiores forças / 3 maiores lacunas

## Avaliação por critério
### Critério N — <nome> — Nota: <0–100>
- **Evidência:** <arquivo / commit / PR / log>
- **Análise:** <justificativa>
- **Para subir a nota:** <ação concreta>

## Contribuição por integrante (git shortlog)
| Integrante | Commits | Linhas (~) | Observação |

## Riscos para a defesa Q&A
- <pontos que o professor provavelmente questionará>

## Recomendações priorizadas
1. <ação de maior impacto>
```

---

## 9. Checklist rápido (✅/❌)

- [ ] App funcional com escopo claro
- [ ] Gerenciador de dependências + lockfile versionado
- [ ] Testes unitários relevantes ao domínio
- [ ] Pipeline CI/CD **sem** GitHub Actions, ≥ 1 job por integrante
- [ ] Commits significativos de **todos** os membros
- [ ] PRs reais com revisão entre membros
- [ ] README: instalação, execução, uso, funcionalidades
- [ ] README: seção "Uso de IA" com ≥ 3 prompts
- [ ] ≥ 5 histórias de usuário com critérios de aceitação e rastreabilidade
- [ ] Metodologia justificada (papéis, cadência, métricas)
- [ ] Dinâmica de desenvolvimento + lições aprendidas
- [ ] Refactorings documentados com evidência
- [ ] Coerência geral requisito → código → teste → pipeline
- [ ] Nenhum eliminatório acionado
