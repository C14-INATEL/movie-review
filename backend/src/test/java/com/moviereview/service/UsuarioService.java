package com.moviereview.service;

import com.moviereview.model.Usuario;
import com.moviereview.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository repository;

    // Construtor com injeção — usado pelo @InjectMocks nos testes com mock
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    // Construtor padrão — mantém compatibilidade com UsuarioServiceTest existente
    public UsuarioService() {
        this.repository = null;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (repository != null) {
            if (repository.existsByEmail(usuario.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
            }
            repository.save(usuario);
            return usuario;
        }

        // Comportamento original sem repositório (para os testes antigos)
        if (emailJaExiste(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
        }
        usuariosEmMemoria.add(usuario);
        return usuario;
    }

    private final List<Usuario> usuariosEmMemoria = new ArrayList<>();

    public boolean emailJaExiste(String email) {
        return usuariosEmMemoria.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuariosEmMemoria);
    }
}