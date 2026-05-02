package com.moviereview.service;

import com.moviereview.model.Usuario;
import com.moviereview.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes com Mock de UsuarioRepository em UsuarioService")
class UsuarioServiceMockTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("M01 – deveChamarSaveNoRepositorioAoCadastrarUsuario")
    void deveChamarSaveNoRepositorioAoCadastrarUsuario() {
        // Arrange
        Usuario usuario = new Usuario("Ana", "ana@email.com", "senha123");

        // O repositório ainda não tem o email cadastrado
        when(repository.existsByEmail("ana@email.com")).thenReturn(false);

        // Act
        usuarioService.cadastrar(usuario);

        // Assert — verifica que repository.save() foi chamado exatamente uma vez com o usuário correto
        verify(repository, times(1)).save(usuario);
    }

    @Test
    @DisplayName("M02 – deveLancarExcecaoQuandoRepositorioRetornaEmailDuplicado")
    void deveLancarExcecaoQuandoRepositorioRetornaEmailDuplicado() {
        // Arrange
        Usuario usuario = new Usuario("Carlos", "carlos@email.com", "abc123");

        // Mock retorna true: o repositório já tem esse email registrado
        when(repository.existsByEmail("carlos@email.com")).thenReturn(true);

        // Act & Assert — espera que o serviço lance IllegalArgumentException
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> usuarioService.cadastrar(usuario)
        );

        assertEquals("Email já cadastrado: carlos@email.com", exception.getMessage());

        // Garante que save() NUNCA foi chamado, pois o email era duplicado
        verify(repository, never()).save(any(Usuario.class));
    }
}
