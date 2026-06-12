package com.moviereview.service;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviereview.model.Filme;

import java.util.List;

public class FilmeServiceTest {

    private FilmeService filmeService;

    @BeforeEach
    void setup() {
        filmeService = new FilmeService();
    }

    @Test
    void deveCadastrarFilmeComSucesso() {

        boolean resultado = filmeService.cadastrarFilme(
                "Batman",
                "Nolan",
                2008
        );

        assertTrue(resultado);
    }

    @Test
    void naoDeveCadastrarFilmeDuplicado() {

        filmeService.cadastrarFilme(
                "Batman",
                "Nolan",
                2008
        );

        boolean resultado = filmeService.cadastrarFilme(
                "Batman",
                "Outro Diretor",
                2020
        );

        assertFalse(resultado);
    }

    @Test
    void deveVerificarFilmeExistente() {

        filmeService.cadastrarFilme(
                "Avatar",
                "James Cameron",
                2009
        );

        boolean resultado = filmeService.verificarExistencia("Avatar");

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoFilmeNaoExiste() {
        boolean resultado = filmeService.verificarExistencia("Vingadores");
        assertFalse(resultado);
    }

    @Test
    void deveListarFilmesQuandoListaVazia() {
        filmeService.listarFilmes(); // cobre o branch isEmpty()
    }

    @Test
    void deveListarFilmesQuandoTemItens() {
        filmeService.cadastrarFilme("Avatar", "Cameron", 2009);
        filmeService.cadastrarFilme("Matrix", "Wachowski", 1999);
        filmeService.listarFilmes(); // cobre o branch com filmes
    }

    @Test
    void deveListarTodosRetornarTodosOsFilmes() {
        filmeService.cadastrarFilme("A", "D1", 2000);
        filmeService.cadastrarFilme("B", "D2", 2001);
        List<Filme> todos = filmeService.listarTodos();
        assertEquals(2, todos.size());
    }

    @Test
    void listarTodosDeveRetornarListaVaziaInicialmente() {
        assertTrue(filmeService.listarTodos().isEmpty());
    }

    @Test
    void deveBuscarFilmePorNome() {
        filmeService.cadastrarFilme("Matrix", "Wachowski", 1999);
        Filme encontrado = filmeService.buscarPorNome("Matrix");
        assertNotNull(encontrado);
        assertEquals("Matrix", encontrado.getTitulo());
    }

    @Test
    void deveRetornarNullParaFilmeNaoEncontrado() {
        assertNull(filmeService.buscarPorNome("Inexistente"));
    }

    @Test
    void deveBuscarFilmePorNomeCaseInsensitive() {
        filmeService.cadastrarFilme("Matrix", "Wachowski", 1999);
        assertNotNull(filmeService.buscarPorNome("MATRIX"));
        assertNotNull(filmeService.buscarPorNome("matrix"));
    }
}