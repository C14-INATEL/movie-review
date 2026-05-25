package com.moviereview.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Adicionais do Modelo Usuario")
class UsuarioModelTest {

    @Test
    @DisplayName("T01 – Usuario deve armazenar nome, email e senha via construtor")
    void deveInstanciarUsuarioComConstrutorCompleto() {
        Usuario usuario = new Usuario("Warley", "warley@email.com", "senha123");

        assertEquals("Warley", usuario.getNome());
        assertEquals("warley@email.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
    }

    @Test
    @DisplayName("T02 – Usuario deve permitir alterar dados via setters")
    void devePermitirAlterarDadosDoUsuario() {
        Usuario usuario = new Usuario("Inicial", "inicial@email.com", "pass");

        usuario.setNome("Ruivo");
        usuario.setEmail("ruivo@email.com");
        usuario.setSenha("novasenha");

        assertEquals("Ruivo", usuario.getNome());
        assertEquals("ruivo@email.com", usuario.getEmail());
        assertEquals("novasenha", usuario.getSenha());
    }
}
