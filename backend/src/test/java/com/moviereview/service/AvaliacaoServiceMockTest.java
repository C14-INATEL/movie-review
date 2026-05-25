package com.moviereview.service;

import com.moviereview.model.Avaliacao;
import com.moviereview.model.Filme;
import com.moviereview.model.Usuario;
import com.moviereview.repository.AvaliacaoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes com Mock de AvaliacaoRepository em AvaliacaoService")
class AvaliacaoServiceMockTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private AvaliacaoRepository repository;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Test
    @DisplayName("M01 – deveChamarSaveNoRepositorioAoAvaliarComSucesso")
    void deveChamarSaveNoRepositorioAoAvaliarComSucesso() {
        Usuario usuario = new Usuario("João", "joao@email.com", "123");
        Filme filme = new Filme("Matrix", "Wachowski", 1999);

        when(usuarioService.existe(usuario)).thenReturn(true);
        when(repository.existsByUsuarioAndFilme(usuario, filme)).thenReturn(false);

        avaliacaoService.avaliar(usuario, filme, 5);

        verify(repository, times(1)).save(any(Avaliacao.class));
    }

    @Test
    @DisplayName("M02 – naoDeveChamarSaveQuandoNotaInvalida")
    void naoDeveChamarSaveQuandoNotaInvalida() {
        Usuario usuario = new Usuario("João", "joao@email.com", "123");
        Filme filme = new Filme("Matrix", "Wachowski", 1999);

        when(usuarioService.existe(usuario)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> avaliacaoService.avaliar(usuario, filme, 6));

        verify(repository, never()).save(any(Avaliacao.class));
    }

    @Test
    @DisplayName("M03 – naoDeveChamarSaveQuandoUsuarioNaoExiste")
    void naoDeveChamarSaveQuandoUsuarioNaoExiste() {
        Usuario usuario = new Usuario("João", "joao@email.com", "123");
        Filme filme = new Filme("Matrix", "Wachowski", 1999);

        when(usuarioService.existe(usuario)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> avaliacaoService.avaliar(usuario, filme, 5));

        verify(repository, never()).save(any(Avaliacao.class));
    }

    @Test
    @DisplayName("M04 – naoDeveChamarSaveQuandoUsuarioJaAvaliouFilme")
    void naoDeveChamarSaveQuandoUsuarioJaAvaliouFilme() {
        Usuario usuario = new Usuario("João", "joao@email.com", "123");
        Filme filme = new Filme("Matrix", "Wachowski", 1999);

        when(usuarioService.existe(usuario)).thenReturn(true);
        when(repository.existsByUsuarioAndFilme(usuario, filme)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> avaliacaoService.avaliar(usuario, filme, 4));

        verify(repository, never()).save(any(Avaliacao.class));
    }
}
