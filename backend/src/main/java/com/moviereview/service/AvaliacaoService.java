package com.moviereview.service;

import com.moviereview.model.Avaliacao;
import com.moviereview.model.Filme;
import com.moviereview.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AvaliacaoService {

    private final UsuarioService usuarioService;
    private final List<Avaliacao> avaliacoes = new ArrayList<>();
    private Long proximoId = 1L;

    public AvaliacaoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Avaliacao avaliar(Usuario usuario, Filme filme, int nota) {
        if (!usuarioService.existe(usuario)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("A nota deve ser entre 1 e 5.");
        }
        if (jaAvaliou(usuario, filme)) {
            throw new IllegalStateException("Usuário já avaliou este filme.");
        }
        Avaliacao avaliacao = new Avaliacao(proximoId++, usuario, filme, nota);
        avaliacoes.add(avaliacao);
        return avaliacao;
    }

    public boolean jaAvaliou(Usuario usuario, Filme filme) {
        return avaliacoes.stream()
                .anyMatch(a -> a.getUsuario() == usuario && a.getFilme() == filme);
    }

    public List<Avaliacao> listarPorFilme(Filme filme) {
        return avaliacoes.stream()
                .filter(a -> a.getFilme() == filme)
                .collect(Collectors.toList());
    }

    public double calcularMedia(Filme filme) {
        List<Avaliacao> porFilme = listarPorFilme(filme);
        if (porFilme.isEmpty()) {
            return 0.0;
        }
        return porFilme.stream()
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }
}
