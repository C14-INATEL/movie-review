package com.moviereview.service;

import com.moviereview.client.FilmeApiClient;
import com.moviereview.model.Filme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuscaFilmeApiServiceTest {

    private FilmeApiClient apiClientMock;
    private BuscaFilmeApiService service;

    @BeforeEach
    void setUp() {
        apiClientMock = mock(FilmeApiClient.class);
        service = new BuscaFilmeApiService(apiClientMock);
    }

    @Test
    void deveBuscarFilmePorTituloNaApiExterna() {
        Filme filmeEsperado = new Filme(1L, "Inception", "Christopher Nolan", 2010);
        when(apiClientMock.buscarPorTitulo("Inception")).thenReturn(filmeEsperado);

        Filme resultado = service.buscar("Inception");

        assertNotNull(resultado);
        assertEquals("Inception", resultado.getTitulo());
        assertEquals("Christopher Nolan", resultado.getDiretor());
        assertEquals(2010, resultado.getAnoLancamento());
        verify(apiClientMock).buscarPorTitulo("Inception");
    }

    @Test
    void deveLancarExcecaoQuandoApiRetornaErro() {
        when(apiClientMock.buscarPorTitulo(any())).thenThrow(new RuntimeException("Falha na API"));

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> service.buscar("Qualquer Titulo"));

        assertEquals("Falha na API", excecao.getMessage());
        verify(apiClientMock).buscarPorTitulo("Qualquer Titulo");
    }
}
