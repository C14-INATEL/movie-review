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

    @Test
    @DisplayName("T05 – Filme equals deve retornar true para mesmo objeto")
    void filmeEqualsParaMesmoObjeto() {
        Filme f = new Filme("Matrix", "W", 1999);
        assertEquals(f, f);
    }

    @Test
    @DisplayName("T06 – Filme equals deve retornar false para null e tipos diferentes")
    void filmeEqualsParaNuloETipoDiferente() {
        Filme f = new Filme("Matrix", "W", 1999);
        assertNotEquals(f, null);
        assertNotEquals(f, "uma string");
    }

    @Test
    @DisplayName("T07 – Filme equals deve retornar false para anos diferentes")
    void filmeEqualsParaAnosDiferentes() {
        assertNotEquals(new Filme("Matrix", "W", 1999), new Filme("Matrix", "W", 2003));
    }

    @Test
    @DisplayName("T08 – Filme hashCode deve ser consistente para objetos iguais")
    void filmeHashCodeConsistente() {
        Filme f1 = new Filme("Matrix", "W", 1999);
        Filme f2 = new Filme("Matrix", "W", 1999);
        assertEquals(f1.hashCode(), f2.hashCode());
    }

    @Test
    @DisplayName("T09 – Usuario equals deve retornar true para mesmo objeto")
    void usuarioEqualsParaMesmoObjeto() {
        Usuario u = new Usuario("A", "a@a.com", "123");
        assertEquals(u, u);
    }

    @Test
    @DisplayName("T10 – Usuario equals deve retornar false para null e tipos diferentes")
    void usuarioEqualsParaNuloETipoDiferente() {
        Usuario u = new Usuario("A", "a@a.com", "123");
        assertNotEquals(u, null);
        assertNotEquals(u, "string");
    }

    @Test
    @DisplayName("T11 – Usuario hashCode deve ser consistente para mesmo email")
    void usuarioHashCodeConsistente() {
        Usuario u1 = new Usuario("Ana", "a@a.com", "111");
        Usuario u2 = new Usuario("Bob", "a@a.com", "222");
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    @DisplayName("T12 – Avaliacao deve expor getId corretamente")
    void avaliacaoGetId() {
        Avaliacao a = new Avaliacao(42L, new Usuario("X", "x@x.com", "p"), new Filme("F", "D", 2000), 3);
        assertEquals(42L, a.getId());
    }
}