package com.moviereview.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.moviereview.service.FilmeService;

@ExtendWith(MockitoExtension.class)
public class MenuMockTest {

    @Mock
    private FilmeService filmeServiceMock; // O "dublê" do seu serviço

    @Test
    public void deveRetornarTrueQuandoFilmeExisteNoBanco() {
        // Configura o Mock: Quando alguém perguntar por "Batman", responda que existe (true)
        when(filmeServiceMock.verificarExistencia("Batman")).thenReturn(true);

        boolean resultado = filmeServiceMock.verificarExistencia("Batman");

        assertTrue(resultado);
        // Verifica se o método foi realmente chamado pelo sistema
        verify(filmeServiceMock).verificarExistencia("Batman");
    }

    @Test
    public void deveRetornarFalseQuandoFilmeNaoExisteNoBanco() {
        // Configura o Mock: Para "Avatar", responda que NÃO existe (false)
        when(filmeServiceMock.verificarExistencia("Avatar")).thenReturn(false);

        boolean resultado = filmeServiceMock.verificarExistencia("Avatar");

        assertFalse(resultado);
        verify(filmeServiceMock).verificarExistencia("Avatar");
    }
}