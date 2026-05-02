package com.moviereview.service;

import com.moviereview.client.FilmeApiClient;
import com.moviereview.model.Filme;

public class BuscaFilmeApiService {

    private final FilmeApiClient apiClient;

    public BuscaFilmeApiService(FilmeApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Filme buscar(String titulo) {
        return apiClient.buscarPorTitulo(titulo);
    }
}
