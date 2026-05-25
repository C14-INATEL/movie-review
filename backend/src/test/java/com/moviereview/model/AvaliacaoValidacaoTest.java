package com.moviereview.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Validação de Nota em Avaliacao")
class AvaliacaoValidacaoTest {

    @Test
    @DisplayName("T01 – setNota deve lançar exceção para nota maior que 5")
    void deveLancarExcecaoParaNotaMaiorQueCinco() {
        Avaliacao avaliacao = new Avaliacao(1L,
                new Usuario("Test", "test@email.com", "123"),
                new Filme("Film", "Dir", 2000),
                3);

        assertThrows(IllegalArgumentException.class, () -> avaliacao.setNota(6));
    }

    @Test
    @DisplayName("T02 – setNota deve lançar exceção para nota menor que 0")
    void deveLancarExcecaoParaNotaMenorQueZero() {
        Avaliacao avaliacao = new Avaliacao(1L,
                new Usuario("Test", "test@email.com", "123"),
                new Filme("Film", "Dir", 2000),
                3);

        assertThrows(IllegalArgumentException.class, () -> avaliacao.setNota(-1));
    }

    @Test
    @DisplayName("T03 – setNota deve aceitar nota válida entre 0 e 5")
    void deveAceitarNotaValida() {
        Avaliacao avaliacao = new Avaliacao(1L,
                new Usuario("Test", "test@email.com", "123"),
                new Filme("Film", "Dir", 2000),
                3);

        avaliacao.setNota(4);

        assertEquals(4, avaliacao.getNota());
    }
}
