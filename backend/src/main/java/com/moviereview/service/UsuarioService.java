package com.moviereview.service;

import java.util.ArrayList;
import java.util.List;

import com.moviereview.model.Usuario;
import com.moviereview.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repository;
    private final List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {
        this.repository = null;
    }

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(String nome, String email, String senha) {

        Usuario newUser = new Usuario(nome,email,senha);
        if (repository != null) {
            if (repository.existsByEmail(newUser.getEmail())) {
                throw new IllegalArgumentException("Email já cadastrado: " + newUser.getEmail());
            }

            repository.save(newUser);
            return newUser;
        }

        if (emailJaExiste(newUser.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + newUser.getEmail());
        }

        usuarios.add(newUser);
        return newUser;
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
