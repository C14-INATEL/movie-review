package com.moviereview;

import com.moviereview.model.Filme;
import com.moviereview.model.Usuario;
import com.moviereview.service.AvaliacaoService;
import com.moviereview.service.FilmeService;
import com.moviereview.service.UsuarioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.*;

class MenuTest {

    private FilmeService filmeService;
    private UsuarioService usuarioService;
    private AvaliacaoService avaliacaoService;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        filmeService = mock(FilmeService.class);
        usuarioService = mock(UsuarioService.class);
        avaliacaoService = mock(AvaliacaoService.class);
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
    }

    @Test
    void opcaoSairDeveEncerrarSemExcecao() {
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(0);
    }

    @Test
    void opcaoListarFilmesDeveChamarServico() {
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(1);
        verify(filmeService).listarFilmes();
    }

    @Test
    void opcaoExibirRankingDeveChamarServico() {
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(4);
        verify(avaliacaoService).exibirRanking(filmeService);
    }

    @Test
    void opcaoInvalidaDeveExecutarSemExcecao() {
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(99);
    }

    @Test
    void opcaoCadastrarUsuarioDeveLerEntradaEChamarServico() {
        String input = "João\njoao@test.com\nsenha123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(2);
        verify(usuarioService).cadastrar("João", "joao@test.com", "senha123");
    }

    @Test
    void opcaoAvaliarFilmeDeveAvaliarQuandoUsuarioEFilmeExistem() {
        Usuario usuario = new Usuario("João", "joao@test.com", "senha");
        Filme filme = new Filme("Matrix", "W", 1999);
        when(usuarioService.buscarPorEmail("joao@test.com")).thenReturn(usuario);
        when(filmeService.buscarPorNome("Matrix")).thenReturn(filme);

        String input = "Matrix\njoao@test.com\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(3);
        verify(avaliacaoService).avaliar(usuario, filme, 5);
    }

    @Test
    void opcaoAvaliarFilmeNaoAvaliaQuandoUsuarioNaoExiste() {
        when(usuarioService.buscarPorEmail("x@test.com")).thenReturn(null);

        String input = "Matrix\nx@test.com\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(3);
        verify(avaliacaoService, never()).avaliar(any(), any(), anyInt());
    }

    @Test
    void opcaoAvaliarFilmeNaoAvaliaQuandoFilmeNaoExiste() {
        Usuario usuario = new Usuario("João", "joao@test.com", "senha");
        when(usuarioService.buscarPorEmail("joao@test.com")).thenReturn(usuario);
        when(filmeService.buscarPorNome("Inexistente")).thenReturn(null);

        String input = "Inexistente\njoao@test.com\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(3);
        verify(avaliacaoService, never()).avaliar(any(), any(), anyInt());
    }

    @Test
    void opcaoCadastrarFilmeDeveChamarServico() {
        String input = "Matrix\nWachowski\n1999\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(5);
        verify(filmeService).cadastrarFilme("Matrix", "Wachowski", 1999);
    }

    @Test
    void opcaoCadastrarFilmeNaoChamaServicoParaAnoInvalido() {
        String input = "Matrix\nWachowski\nabc\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Menu menu = new Menu(filmeService, usuarioService, avaliacaoService);
        menu.processarOpcao(5);
        verify(filmeService, never()).cadastrarFilme(any(), any(), anyInt());
    }
}
