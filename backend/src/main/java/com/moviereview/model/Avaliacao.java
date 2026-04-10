package com.moviereview.model;

public class Avaliacao {

    private Long id;
    private Usuario usuario;
    private Filme filme;
    private int nota; // valor entre 1 e 5

    // Construtor vazio
    public Avaliacao() {
    }

    // Construtor completo
    public Avaliacao(Long id, Usuario usuario, Filme filme, int nota) {
        this.id = id;
        this.usuario = usuario;
        this.filme = filme;
        this.nota = nota;
    }

    // Getter e Setter do id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter do usuario
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getter e Setter do filme
    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    // Getter e Setter da nota
    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
