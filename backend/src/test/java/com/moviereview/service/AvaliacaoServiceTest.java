package com.moviereview.service;

import com.moviereview.model.Avaliacao;
import com.moviereview.model.Filme;
import com.moviereview.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AvaliacaoServiceTest {

    private AvaliacaoService avaliacaoService;
    private UsuarioService usuarioService;
    private Usuario usuario;
    private Filme filme;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
        avaliacaoService = new AvaliacaoService(usuarioService);
        usuario = new Usuario("João", "joao@email.com", "senha123");
        usuarioService.cadastrar(usuario);
        filme = new Filme("Matrix", "Wachowski", 1999);
    }

    @Test
    void deveRegistrarAvaliacaoComSucesso() {
        Avaliacao avaliacao = avaliacaoService.avaliar(usuario, filme, 5);

        assertNotNull(avaliacao);
        assertEquals(usuario, avaliacao.getUsuario());
        assertEquals(filme, avaliacao.getFilme());
        assertEquals(5, avaliacao.getNota());
    }

    @Test
    void deveLancarExcecaoQuandoNotaMenorQueUm() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> avaliacaoService.avaliar(usuario, filme, 0)
        );

        assertEquals("A nota deve ser entre 1 e 5.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNotaMaiorQueCinco() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> avaliacaoService.avaliar(usuario, filme, 6)
        );

        assertEquals("A nota deve ser entre 1 e 5.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioJaAvaliouFilme() {
        avaliacaoService.avaliar(usuario, filme, 4);

        assertThrows(
                IllegalStateException.class,
                () -> avaliacaoService.avaliar(usuario, filme, 3)
        );
    }

    @Test
    void devePermitirMesmousuarioAvaliarFilmesDiferentes() {
        Filme outroFilme = new Filme("Interestelar", "Christopher Nolan", 2014);

        Avaliacao av1 = avaliacaoService.avaliar(usuario, filme, 5);
        Avaliacao av2 = avaliacaoService.avaliar(usuario, outroFilme, 4);

        assertNotNull(av1);
        assertNotNull(av2);
    }

    @Test
    void deveListarAvaliacoesPorFilme() {
        Usuario outroUsuario = new Usuario("Maria", "maria@email.com", "senha456");
        usuarioService.cadastrar(outroUsuario);

        avaliacaoService.avaliar(usuario, filme, 5);
        avaliacaoService.avaliar(outroUsuario, filme, 3);

        List<Avaliacao> avaliacoes = avaliacaoService.listarPorFilme(filme);

        assertEquals(2, avaliacoes.size());
    }

    @Test
    void deveCalcularMediaDasNotas() {
        Usuario outroUsuario = new Usuario("Maria", "maria@email.com", "senha456");
        usuarioService.cadastrar(outroUsuario);

        avaliacaoService.avaliar(usuario, filme, 4);
        avaliacaoService.avaliar(outroUsuario, filme, 2);

        double media = avaliacaoService.calcularMedia(filme);

        assertEquals(3.0, media);
    }

    @Test
    void deveRetornarZeroNaMediaQuandoFilmeSemAvaliacoes() {
        double media = avaliacaoService.calcularMedia(filme);

        assertEquals(0.0, media);
    }

    @Test
    void deveRetornarFalseQuandoUsuarioAindaNaoAvaliou() {
        assertFalse(avaliacaoService.jaAvaliou(usuario, filme));
    }

    @Test
    void deveRetornarTrueQuandoUsuarioJaAvaliou() {
        avaliacaoService.avaliar(usuario, filme, 3);

        assertTrue(avaliacaoService.jaAvaliou(usuario, filme));
    }

    @Test
    void deveLancarExcecaoSeUsuarioNaoExisteAoAvaliar() {
        UsuarioService usuarioServiceMock = Mockito.mock(UsuarioService.class);
        when(usuarioServiceMock.existe(usuario)).thenReturn(false);
        AvaliacaoService service = new AvaliacaoService(usuarioServiceMock);

        assertThrows(IllegalArgumentException.class, () -> service.avaliar(usuario, filme, 5));
    }

    @Test
    void deveRegistrarAvaliacaoQuandoUsuarioExiste() {
        UsuarioService usuarioServiceMock = Mockito.mock(UsuarioService.class);
        when(usuarioServiceMock.existe(usuario)).thenReturn(true);
        AvaliacaoService service = new AvaliacaoService(usuarioServiceMock);

        Avaliacao avaliacao = service.avaliar(usuario, filme, 5);

        assertNotNull(avaliacao);
        assertEquals(usuario, avaliacao.getUsuario());
        assertEquals(filme, avaliacao.getFilme());
        assertEquals(5, avaliacao.getNota());
    }
}
