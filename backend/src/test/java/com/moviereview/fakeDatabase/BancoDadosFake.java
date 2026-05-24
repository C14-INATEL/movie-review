package com.moviereview.fakeDatabase;

import com.moviereview.model.Usuario;
import com.moviereview.model.Filme;
import com.moviereview.model.Avaliacao;

import java.util.ArrayList;
import java.util.List;

// Classe que simula um banco de dados em memória usando ArrayLists.
public class BancoDadosFake {

    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<Filme> filmes = new ArrayList<>();
    private static final List<Avaliacao> avaliacoes = new ArrayList<>();

    public static List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public static void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public static List<Filme> getFilmes() {
        return new ArrayList<>(filmes);
    }

    public static void adicionarFilme(Filme filme) {
        filmes.add(filme);
    }

    public static void removerFilme(Filme filme) {
        filmes.remove(filme);
    }

    public static List<Avaliacao> getAvaliacoes() {
        return new ArrayList<>(avaliacoes);
    }

    public static void adicionarAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public static void removerAvaliacao(Avaliacao avaliacao) {
        avaliacoes.remove(avaliacao);
    }
}
