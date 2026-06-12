# História de Usuário — Avaliação de Filmes

## História
Como usuário cadastrado, eu quero avaliar um filme com uma nota de 1 a 5 para que minha opinião fique registrada e contribua para a média de avaliações do filme.

## Critérios de Aceitação
- **Given** que o usuário está cadastrado no sistema e ainda não avaliou o filme
- **When** ele submete uma avaliação com nota entre 1 e 5
- **Then** a avaliação é persistida via `AvaliacaoRepository` e associada ao usuário e ao filme

---

- **Given** que o usuário tenta avaliar um filme com nota 0 ou nota 6
- **When** a avaliação é submetida
- **Then** o sistema lança `IllegalArgumentException` com a mensagem "Nota deve ser entre 1 e 5"

---

- **Given** que o usuário já avaliou um determinado filme
- **When** ele tenta submeter uma segunda avaliação para o mesmo filme
- **Then** o sistema lança `IllegalStateException` impedindo a duplicidade

---

- **Given** que o usuário não está cadastrado no sistema
- **When** ele tenta submeter uma avaliação
- **Then** o sistema lança `IllegalArgumentException` com a mensagem "Usuário não encontrado."

## Rastreabilidade
- **Issue/PR:** PR appmod/java-upgrade (AvaliacaoRepository + AvaliacaoService)
- **Teste automatizado:** AvaliacaoServiceMockTest.java

## Prioridade
Alta

## Status
Entregue
