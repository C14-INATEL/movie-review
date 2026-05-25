package com.moviereview.repository;

import com.moviereview.model.Avaliacao;
import com.moviereview.model.Filme;
import com.moviereview.model.Usuario;

import java.util.List;

public interface AvaliacaoRepository {

    void save(Avaliacao avaliacao);

    List<Avaliacao> findByFilme(Filme filme);

    boolean existsByUsuarioAndFilme(Usuario usuario, Filme filme);
}
