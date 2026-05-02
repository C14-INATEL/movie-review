package com.moviereview.client;

import com.moviereview.model.Filme;

public interface FilmeApiClient {
    Filme buscarPorTitulo(String titulo);
}
