package com.moviereview;

import java.util.Scanner;

import com.moviereview.service.UsuarioService;

import service.FilmeService;

public class Menu {

    private Scanner leitor = new Scanner(System.in);
    private FilmeService filmeService;
    private UsuarioService usuarioService;

    // Construtor necessário para receber o Mock do teste
    public Menu(FilmeService filmeService, UsuarioService usuarioService) {
        this.filmeService = filmeService;
        this.usuarioService = usuarioService;
    }

    public void exibirMenu() {
        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\n=======================================");
            System.out.println("      MOVIE REVIEW SYSTEM - C14        ");
            System.out.println("=======================================");
            System.out.println("  [1] Listar Catálogo de Filmes");
            System.out.println("  [2] Cadastrar Novo Usuário");
            System.out.println("  [3] Avaliar um Filme");
            System.out.println("  [4] Ver Ranking de Notas");
            System.out.println("  [5] cadastrar Filme");
            System.out.println("  [0] Sair do Sistema");
            System.out.println("=======================================");
            System.out.print(">> Escolha uma opção: ");

            String entrada = leitor.nextLine();
            escolha = Integer.parseInt(entrada);

            switch (escolha) {
                case 0:
                    System.out.println("\nEncerrando sistema... Até logo!");
                    escolha = 0;
                    break;

                case 1:
                        filmeService.listarFilmes();
                    break;
                case 2:
                    System.out.println("informe o nome do usuario");
                    String nome = leitor.nextLine();

                    System.out.println("informe o email do usuario");
                    String email = leitor.nextLine();

                    System.out.println("informe a senha do usuario");
                    String senha = leitor.nextLine();

                    usuarioService.cadastrar(nome, email, senha);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("informe o nome do filme:");
                    String nomeFilme = leitor.nextLine();

                    System.out.println("informe o nome do diretor:");
                    String diretor = leitor.nextLine();

                    System.out.println("informe o ano de lançamento:");
                    int anoLancamento = Integer.parseInt(leitor.nextLine());

                    filmeService.cadastrarFilme(nomeFilme, diretor, anoLancamento);
                    break;
                default:
                    throw new AssertionError();
            }

        }
    }

    public static void main(String[] args) {
        // Criamos o serviço real aqui para o sistema funcionar normalmente
        FilmeService fs = new FilmeService();
        UsuarioService us = new UsuarioService();
        Menu meuMenu = new Menu(fs, us);
        meuMenu.exibirMenu();
    }
}
