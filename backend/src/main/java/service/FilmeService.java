package service;

import java.util.ArrayList;
import java.util.List;

import com.moviereview.model.Filme;

public class FilmeService {

    private final List<Filme> filmes = new ArrayList<>();

    public boolean cadastrarFilme(String nome, String diretor, int anoLancamento) {

        Filme novoFilme = new Filme(nome, diretor, anoLancamento);

        try {
            if (!verificarExistencia(nome)) {
                filmes.add(novoFilme);
                System.out.println("filme cadastrado!");
                return true;
            } else {
                System.out.println("filme já existe!");
                return false;
            }

        } catch (Exception e) {
            System.out.println("erro ao cadastrar");
            return false;
        }
    }

    public boolean verificarExistencia(String nomeFilme) {
        return filmes.stream()
                .anyMatch(u -> u.getTitulo().equalsIgnoreCase(nomeFilme));
    }

    public void listarFilmes() {
        if(filmes.isEmpty()){
            System.out.println("nenhum filme disponivel");
        }else{
            for (Filme filme : filmes) {
            System.out.println(filme.getTitulo());
        }
        }
        
    }
}
