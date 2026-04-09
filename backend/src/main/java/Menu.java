import java.util.Scanner;

public class Menu {
    private Scanner leitor = new Scanner(System.in);

    // Método que será o alvo dos 4 testes unitários da Tarefa 3
    public boolean validarOpcao(int opcao) {
        return opcao >= 0 && opcao <= 4;
    }

    public void exibirMenu() {
        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\n=======================================");
            System.out.println("       MOVIE REVIEW SYSTEM - C14       ");
            System.out.println("=======================================");
            System.out.println("  [1] Listar Catálogo de Filmes");
            System.out.println("  [2] Cadastrar Novo Usuário");
            System.out.println("  [3] Avaliar um Filme");
            System.out.println("  [4] Ver Ranking de Notas");
            System.out.println("  [0] Sair do Sistema");
            System.out.println("=======================================");
            System.out.print(">> Escolha uma opção: ");

            try {
                String entrada = leitor.nextLine();
                escolha = Integer.parseInt(entrada);

                if (validarOpcao(escolha)) {
                    processarAcao(escolha);
                } else {
                    System.out.println("\n[!] ERRO: Opção inválida (digite de 0 a 4).");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[!] ERRO: Por favor, digite apenas números inteiros.");
            }
        }
    }

    private void processarAcao(int opcao) {
        if (opcao == 0) {
            System.out.println("\nEncerrando sistema... Até logo!");
        } else {
            System.out.println("\n[Aviso] Você acessou a funcionalidade " + opcao);
            System.out.println("Pressione ENTER para voltar ao menu...");
            leitor.nextLine();
        }
    }

    // Método principal para você conseguir RODAR o código no VS Code
    public static void main(String[] args) {
        Menu meuMenu = new Menu();
        meuMenu.exibirMenu();
    }
}