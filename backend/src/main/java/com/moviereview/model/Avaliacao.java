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
        setNota(nota);
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Filme getFilme() { return filme; }
    public int getNota() { return nota; }

    public void setNota(int nota) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("Nota deve ser entre 1 e 5");
        }
        this.nota = nota;
    }
}
