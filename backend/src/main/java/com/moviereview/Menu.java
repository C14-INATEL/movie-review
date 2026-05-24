package com.moviereview;

import java.util.Scanner;

public class Menu {
    private final Scanner leitor = new Scanner(System.in);

    public void exibirOpcoes() {
        System.out.println("--- SISTEMA DE FILMES (GRUPO C14) ---");
        System.out.println("1 - Listar Catálogo");
        System.out.println("2 - Cadastrar Usuário");
        System.out.println("3 - Avaliar um Filme");
        System.out.println("4 - Ver Ranking de Notas");
        System.out.println("0 - Sair");
        System.out.print("Digite a opção: ");

        int escolha = leitor.nextInt();
        System.out.println("Opção " + escolha + " selecionada. (Em desenvolvimento)");
    }
}
