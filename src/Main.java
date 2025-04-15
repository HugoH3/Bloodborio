import Battle.Coliseu;
import Battle.FlorestaManager;
import Battle.Loja;
import Entities.Jogador;
import Entities.Personagem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogador jogador = null;

        System.out.println("\uD83C\uDFAE Bem-vindo ao RPG de Batalha!");

        // Cadastro
        System.out.println("\n--- Cadastro ---");
        System.out.print("Digite seu nome de jogador: ");
        String nome = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        jogador = new Jogador(1, nome, senha, 100, null);

        // Login
        System.out.println("\n--- Login ---");
        boolean autenticado = false;
        while (!autenticado) {
            System.out.print("Usuário: ");
            String login = scanner.nextLine();
            System.out.print("Senha: ");
            String tentativa = scanner.nextLine();
            if (jogador.autenticar(login, tentativa)) {
                System.out.println("✅ Login bem-sucedido!");
                autenticado = true;
            } else {
                System.out.println("❌ Usuário ou senha incorretos. Tente novamente.");
            }
        }

        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Criar Personagem");
            System.out.println("2 - Ir para a Floresta 🌲");
            System.out.println("3 - Ir para a Casa 🏠");
            System.out.println("4 - Loja 🛒");
            System.out.println("5 - Usar item 🎒");
            System.out.println("6 - Coliseu");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do personagem: ");
                    String nomePers = scanner.nextLine();
                    jogador.criarPersonagem(nomePers);
                    break;

                case 2:
                    Personagem explorador = jogador.selecionarPersonagemPorScanner(scanner);
                    if (explorador != null) {
                        FlorestaManager.explorarFloresta(explorador, jogador, scanner);
                    }
                    break;

                case 3:
                    Personagem p = jogador.selecionarPersonagemPorScanner(scanner);
                    if (p != null) {
                        p.recuperarVidaTotal();
                        p.recuperarManaTotal();
                        p.recuperarVigorTotal();
                        System.out.println("✅ Recuperado com sucesso na Casa!");
                    }
                    break;

                case 4:
                    Loja.abrirLoja(jogador, scanner);
                    break;

                case 5:
                    Personagem alvo = jogador.selecionarPersonagemPorScanner(scanner);
                    if (alvo != null) {
                        alvo.usarItem(scanner);
                    }
                    break;
                case 6:
                    Coliseu.iniciarPvP(jogador.getPersonagens(), scanner);
                    break;

                case 7:
                    System.out.println("👋 Até logo!");
                    break;

                default:
                    System.out.println("❌ Opção inválida.");
            }
        } while (opcao != 6);
    }
}