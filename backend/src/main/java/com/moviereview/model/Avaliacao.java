package com.moviereview.model;

public class Avaliacao {
    private Long id;
    private Usuario usuario;
    private Filme filme;
    private int nota;

    public Avaliacao(Long id, Usuario usuario, Filme filme, int nota) {
        this.id = id;
        this.usuario = usuario;
        this.filme = filme;
        this.nota = nota;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Filme getFilme() { return filme; }
    public int getNota() { return nota; }
}