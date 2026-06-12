package com.moviereview.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviereview.model.Usuario;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {

        Usuario cadastrado =
                usuarioService.cadastrar(
                        "João",
                        "joao@email.com",
                        "senha123"
                );

        assertNotNull(cadastrado);
        assertEquals("joao@email.com", cadastrado.getEmail());
    }

    @Test
    void naoDeveCadastrarEmailDuplicado() {

        usuarioService.cadastrar(
                "João",
                "joao@email.com",
                "senha123"
        );

        Usuario usuarioDuplicado =
                usuarioService.cadastrar(
                        "João Silva",
                        "joao@email.com",
                        "outrasenha"
                );

        assertNull(usuarioDuplicado);
    }

    @Test
    void deveRetornarTrueQuandoEmailJaExiste() {

        usuarioService.cadastrar(
                "João",
                "joao@email.com",
                "senha123"
        );

        assertTrue(
                usuarioService.emailJaExiste(
                        "joao@email.com"
                )
        );
    }

    @Test
    void deveRetornarFalseQuandoEmailNaoExiste() {

        assertFalse(
                usuarioService.emailJaExiste(
                        "naoexiste@email.com"
                )
        );
    }

    @Test
    void deveValidarEmailCaseInsensitive() {

        usuarioService.cadastrar(
                "João",
                "joao@email.com",
                "senha123"
        );

        assertTrue(
                usuarioService.emailJaExiste(
                        "JOAO@EMAIL.COM"
                )
        );
    }

    @Test
    void deveCadastrarUsuarioPreservandoTodosOsDados() {

        Usuario cadastrado =
                usuarioService.cadastrar(
                        "João",
                        "joao@email.com",
                        "senha123"
                );

        assertEquals("João", cadastrado.getNome());
        assertEquals("joao@email.com", cadastrado.getEmail());
        assertEquals("senha123", cadastrado.getSenha());
    }

    @Test
    void deveListarTodosOsUsuariosCadastrados() {

        usuarioService.cadastrar(
                "João",
                "joao@email.com",
                "senha123"
        );

        usuarioService.cadastrar(
                "Maria",
                "maria@email.com",
                "senha456"
        );

        List<Usuario> lista = usuarioService.listarTodos();

        assertEquals(2, lista.size());
    }

    @Test
    void listarTodosDeveRetornarListaVaziaQuandoNaoHaUsuarios() {

        List<Usuario> lista = usuarioService.listarTodos();

        assertTrue(lista.isEmpty());
    }

    @Test
    void listarTodosDeveRetornarCopiaDefensiva() {

        usuarioService.cadastrar(
                "João",
                "joao@email.com",
                "senha123"
        );

        List<Usuario> lista = usuarioService.listarTodos();

        lista.clear();

        assertEquals(
                1,
                usuarioService.listarTodos().size()
        );
    }

    @Test
    void deveCadastrarVariosUsuariosComEmailsDiferentes() {

        usuarioService.cadastrar(
                "João",
                "joao@email.com",
                "senha123"
        );

        usuarioService.cadastrar(
                "Maria",
                "maria@email.com",
                "senha456"
        );

        usuarioService.cadastrar(
                "Carlos",
                "carlos@email.com",
                "senha789"
        );

        assertEquals(
                3,
                usuarioService.listarTodos().size()
        );
    }

    @Test
    void deveRetornarTrueQuandoUsuarioExiste() {

        Usuario usuario =
                usuarioService.cadastrar(
                        "João",
                        "joao@email.com",
                        "senha123"
                );

        assertTrue(
                usuarioService.existe(usuario)
        );
    }

    @Test
    void deveRetornarFalseQuandoUsuarioNaoExiste() {
        Usuario usuario = new Usuario("Pedro", "pedro@email.com", "123");
        assertFalse(usuarioService.existe(usuario));
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        usuarioService.cadastrar("João", "joao@email.com", "senha");
        Usuario encontrado = usuarioService.buscarPorEmail("joao@email.com");
        assertNotNull(encontrado);
        assertEquals("João", encontrado.getNome());
    }

    @Test
    void deveRetornarNullParaEmailNaoEncontrado() {
        assertNull(usuarioService.buscarPorEmail("naoexiste@email.com"));
    }

    @Test
    void deveBuscarPorEmailCaseInsensitive() {
        usuarioService.cadastrar("João", "joao@email.com", "senha");
        assertNotNull(usuarioService.buscarPorEmail("JOAO@EMAIL.COM"));
    }
}