package com.moviereview.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 Testes unitários das classes de modelo - Movie Review
 Matheus Henrique
*/
@DisplayName("Testes das Classes de Modelo")
class ModeloTest {

    @Test
    @DisplayName("T01 – Filme deve armazenar título, diretor e ano corretamente")
    void deveArmazenarDadosDoFilmeCorretamente() {
        Filme filme = new Filme("Interestelar", "Christopher Nolan", 2014);

        assertEquals("Interestelar", filme.getTitulo());
        assertEquals("Christopher Nolan", filme.getDiretor());
        assertEquals(2014, filme.getAnoLancamento());
    }

    @Test
    @DisplayName("T02 – Usuario deve armazenar nome, email e senha corretamente")
    void deveArmazenarDadosDoUsuarioCorretamente() {
        Usuario usuario = new Usuario("Ana", "ana@email.com", "senha123");

        assertEquals("Ana", usuario.getNome());
        assertEquals("ana@email.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
    }

    @Test
    @DisplayName("T03 – Avaliacao deve vincular usuario, filme e nota corretamente")
    void deveVincularUsuarioFilmeENotaNaAvaliacao() {
        Usuario usuario = new Usuario("Carlos", "carlos@email.com", "abc");
        Filme filme = new Filme("Matrix", "Wachowski", 1999);
        Avaliacao avaliacao = new Avaliacao(1L, usuario, filme, 5);

        assertEquals(usuario, avaliacao.getUsuario());
        assertEquals(filme, avaliacao.getFilme());
        assertEquals(5, avaliacao.getNota());
        assertEquals(1L, avaliacao.getId());
    }

    @Test
    @DisplayName("T04 – Filme deve permitir atualizar titulo via setter")
    void deveAtualizarTituloDoFilmeViaSetterr() {
        Filme filme = new Filme("Titulo Antigo", "Diretor", 2000);
        filme.setTitulo("Titulo Novo");

        assertEquals("Titulo Novo", filme.getTitulo());
    }
}