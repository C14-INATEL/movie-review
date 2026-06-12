## Histórias de Usuários

Esta seção apresenta as histórias de usuário desenvolvidas ao longo do projeto, demonstrando a aplicação dos conceitos de Engenharia de Software e a rastreabilidade entre requisitos, implementação e testes automatizados.

### US06 – Avaliar Filmes com Armazenamento em Memória

- **Como** desenvolvedor do sistema Movie Review,

- **Eu quero** implementar um sistema robusto de avaliações com validação rigorosa e armazenamento em memória,

- **Para que** os usuários possam registrar suas opiniões sobre filmes sem duplicação de avaliações, garantindo integridade dos dados em tempo de execução.

#### Critérios de Aceitação

##### Cenário 1 – Registrar avaliação com sucesso

- **Given** que um usuário e um filme existem no sistema

- **When** o usuário submete uma avaliação com nota entre 1 e 5

- **Then** a avaliação é armazenada em memória na lista de `avaliacoes` com um ID sequencial gerado automaticamente

##### Cenário 2 – Validação de intervalo de notas

- **Given** que uma avaliação está sendo registrada

- **When** a nota informada é menor que 1 ou maior que 5

- **Then** o sistema lança `IllegalArgumentException` com a mensagem "A nota deve ser entre 1 e 5."

##### Cenário 3 – Prevenção de avaliação duplicada

- **Given** que um usuário já avaliou um filme específico

- **When** ele tenta submeter uma segunda avaliação para o mesmo filme

- **Then** o sistema lança `IllegalStateException` impedindo a duplicidade e garantindo uma nota por usuário/filme

##### Cenário 4 – Cálculo de média de avaliações

- **Given** que um filme possui múltiplas avaliações armazenadas em memória

- **When** `calcularMedia()` é chamado para esse filme

- **Then** a média aritmética das notas é retornada corretamente, ou 0.0 se nenhuma avaliação existe

##### Cenário 5 – Listagem de avaliações por filme

- **Given** que existem avaliações armazenadas em memória

- **When** `listarPorFilme()` é invocado com um filme específico

- **Then** apenas as avaliações referentes ao filme são retornadas em uma nova lista

#### Prioridade

- Alta

#### Status

- Em Desenvolvimento

#### Rastreabilidade

**Branch:** feature/repositorio-memoria

**Classes envolvidas:**

* `AvaliacaoService.java` - Métodos `avaliar()`, `jaAvaliou()`, `listarPorFilme()`, `calcularMedia()`
* `Avaliacao.java` - Entidade com validação de nota
* `UsuarioService.java` - Gerenciamento de usuários em memória
* `Menu.java` - Interface de entrada para seleção de avaliação

**Testes Automatizados:**

* `AvaliacaoServiceTest.java` - Testes de validação e cálculo de média
* `AvaliacaoValidacaoTest.java` - Testes específicos de validação

#### Decisões Técnicas

**1. Armazenamento em Memória**
- Repositório em memória utilizando `List<Avaliacao>` para simplificar a arquitetura
- Eliminação da abstração de Repository Pattern para reduzir complexidade
- Dados persistem apenas durante a execução da aplicação

**2. Comparação por Identidade**
- Uso de `==` para comparação de objetos `Usuario` e `Filme` em vez de `.equals()`
- Garante que comparações se baseiem na instância do objeto, não em valores

**3. ID Sequencial**
- Implementação de `proximoId` para geração automática de IDs únicos
- Incremento atômico com `proximoId++` durante cada avaliação

**4. Validação em Tempo de Runtime**
- Validações imediatas no método `avaliar()` lançando exceções apropriadas
- Crítico para manter integridade dos dados sem persistência

#### Fluxo de Interação

1. Usuário seleciona a opção **[3] Avaliar um Filme** no menu
2. Sistema solicita nome do filme a ser avaliado
3. Sistema solicita email do usuário avaliador
4. Sistema solicita nota do filme (1 a 5)
5. `AvaliacaoService.avaliar()` é invocado com usuario, filme e nota
6. Validações ocorrem:
   - Verificação: está entre 1 e 5?
   - Verificação: usuário já avaliou este filme?
7. Se passou em todas validações:
   - Nova `Avaliacao` é criada com ID sequencial
   - Avaliação é armazenada em `avaliacoes` (memória)
   - Retorna `Avaliacao` criada
8. Se validação falhar:
   - Lança exceção apropriada
   - Menu permite nova tentativa

#### Exemplo de Código - Fluxo de Validação

```java
public Avaliacao avaliar(Usuario usuario, Filme filme, int nota) {
    // Validação 1: Intervalo de notas
    if (nota < 1 || nota > 5) {
        throw new IllegalArgumentException("A nota deve ser entre 1 e 5.");
    }
    
    // Validação 2: Ausência de duplicação
    if (jaAvaliou(usuario, filme)) {
        throw new IllegalStateException("Usuário já avaliou este filme.");
    }
    
    // Criar e armazenar em memória
    Avaliacao avaliacao = new Avaliacao(proximoId++, usuario, filme, nota);
    avaliacoes.add(avaliacao);
    return avaliacao;
}