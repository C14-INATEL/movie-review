package com.moviereview.service;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviereview.service.FilmeService;

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
}