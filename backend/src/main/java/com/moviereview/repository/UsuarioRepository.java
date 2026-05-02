package com.moviereview.repository;

import com.moviereview.model.Usuario;

public interface UsuarioRepository {

    void save(Usuario usuario);

    boolean existsByEmail(String email);
}