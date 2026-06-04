package com.moviereview.model;

public class Filme {
    private String titulo;
    private String diretor;
    private int anoLancamento;

    public Filme(String titulo, String diretor, int anoLancamento) {
        this.titulo = titulo;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDiretor() { return diretor; }
    public int getAnoLancamento() { return anoLancamento; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Filme)) return false;
        Filme outro = (Filme) obj;
        return anoLancamento == outro.anoLancamento
                && titulo != null
                && titulo.equalsIgnoreCase(outro.titulo);
    }

    @Override
    public int hashCode() {
        int result = titulo == null ? 0 : titulo.toLowerCase().hashCode();
        result = 31 * result + anoLancamento;
        return result;
    }
}
