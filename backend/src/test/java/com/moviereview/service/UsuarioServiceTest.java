package com.moviereview.service;

import com.moviereview.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        Usuario usuario = new Usuario("João", "joao@email.com", "senha123");

        Usuario cadastrado = usuarioService.cadastrar(usuario);

        assertNotNull(cadastrado);
        assertEquals("joao@email.com", cadastrado.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaCadastrado() {
        Usuario usuario1 = new Usuario("João", "joao@email.com", "senha123");
        Usuario usuario2 = new Usuario("João Silva", "joao@email.com", "outrasenha");

        usuarioService.cadastrar(usuario1);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrar(usuario2)
        );

        assertEquals("Email já cadastrado: joao@email.com", exception.getMessage());
    }

    @Test
    void deveRetornarTrueQuandoEmailJaExiste() {
        usuarioService.cadastrar(new Usuario("João", "joao@email.com", "senha123"));

        assertTrue(usuarioService.emailJaExiste("joao@email.com"));
    }

    @Test
    void deveRetornarFalseQuandoEmailNaoExiste() {
        assertFalse(usuarioService.emailJaExiste("naoexiste@email.com"));
    }

    @Test
    void deveValidarEmailCaseInsensitive() {
        usuarioService.cadastrar(new Usuario("João", "joao@email.com", "senha123"));

        assertTrue(usuarioService.emailJaExiste("JOAO@EMAIL.COM"));
    }

    @Test
    void deveListarTodosOsUsuariosCadastrados() {
        usuarioService.cadastrar(new Usuario("João", "joao@email.com", "senha123"));
        usuarioService.cadastrar(new Usuario("Maria", "maria@email.com", "senha456"));

        List<Usuario> lista = usuarioService.listarTodos();

        assertEquals(2, lista.size());
    }

    @Test
    void listarTodosDeveRetornarListaVaziaQuandoNaoHaUsuarios() {
        List<Usuario> lista = usuarioService.listarTodos();

        assertTrue(lista.isEmpty());
    }
}
