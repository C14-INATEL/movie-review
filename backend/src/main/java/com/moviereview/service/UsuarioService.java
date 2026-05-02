package com.moviereview.service;

import com.moviereview.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public Usuario cadastrar(Usuario usuario) {
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

    public boolean existe(Usuario usuario) {
        return usuarios.contains(usuario);
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
}
