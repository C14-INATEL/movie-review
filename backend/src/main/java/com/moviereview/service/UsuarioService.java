package com.moviereview.service;

import com.moviereview.model.Usuario;
import com.moviereview.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository repository;
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        this.repository = null;
    }

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (repository != null) {
            if (repository.existsByEmail(usuario.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
            }

            repository.save(usuario);
            return usuario;
        }

        if (emailJaExiste(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
        }

        usuarios.add(usuario);
        return usuario;
    }

    public boolean emailJaExiste(String email) {
        return usuarios.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
}
