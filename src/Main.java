import Battle.Coliseu;
import Battle.FlorestaManager;
import Battle.Loja;
import Entities.Jogador;
import Entities.ListaDeJogadores;
import Entities.Personagem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDeJogadores banco = new ListaDeJogadores();
        Jogador jogadorLogado = null;

        System.out.println("🎮 Bem-vindo ao RPG de Batalha!");

        // === MENU INICIAL ===
        while (jogadorLogado == null) {
            System.out.println("\n--- Menu Inicial ---");
            System.out.println("1 - Novo Jogador");
            System.out.println("2 - Entrar com Jogador Existente");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                System.out.print("Digite seu nome de jogador: ");
                String nome = scanner.nextLine();

                if (banco.nomeExiste(nome)) {
                    System.out.println("❌ Nome já está em uso.");
                    continue;
                }

                System.out.print("Digite sua senha: ");
                String senha = scanner.nextLine();
                Jogador novo = new Jogador(1, nome, senha, 100, null);
                banco.adicionarJogador(novo);
                System.out.println("✅ Jogador criado com sucesso! Faça login para continuar.");
            } else if (escolha == 2) {
                System.out.print("Usuário: ");
                String login = scanner.nextLine();
                System.out.print("Senha: ");
                String tentativa = scanner.nextLine();

                Jogador encontrado = banco.buscarPorLogin(login, tentativa);
                if (encontrado != null) {
                    jogadorLogado = encontrado;
                    System.out.println("✅ Login bem-sucedido!");
                } else {
                    System.out.println("❌ Usuário ou senha incorretos.");
                }
            } else {
                System.out.println("❌ Opção inválida.");
            }
        }

        // === MENU PRINCIPAL ===
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Criar Personagem");
            System.out.println("2 - Ir para a Floresta 🌲");
            System.out.println("3 - Ir para a Casa 🏠");
            System.out.println("4 - Loja 🛒");
            System.out.println("5 - Usar item 🎒");
            System.out.println("6 - Coliseu 🏟️");
            System.out.println("7 - Sair");
            System.out.println("8 - Trocar de Conta");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do personagem: ");
                    String nomePers = scanner.nextLine();
                    jogadorLogado.criarPersonagem(nomePers);
                    break;

                case 2:
                    Personagem explorador = jogadorLogado.selecionarPersonagemPorScanner(scanner);
                    if (explorador != null) {
                        FlorestaManager.explorarFloresta(explorador, jogadorLogado, scanner);
                    }
                    break;

                case 3:
                    Personagem p = jogadorLogado.selecionarPersonagemPorScanner(scanner);
                    if (p != null) {
                        p.recuperarVidaTotal();
                        p.recuperarManaTotal();
                        p.recuperarVigorTotal();
                        System.out.println("✅ Recuperado com sucesso na Casa!");
                    }
                    break;

                case 4:
                    Loja.abrirLoja(jogadorLogado, scanner);
                    break;

                case 5:
                    Personagem alvo = jogadorLogado.selecionarPersonagemPorScanner(scanner);
                    if (alvo != null) {
                        alvo.usarItem(scanner);
                    }
                    break;

                case 6:
                    Coliseu.iniciarPvPDoisJogadores(banco, scanner);
                    break;

                case 7:
                    System.out.println("👋 Até logo!");
                    break;
                case 8:
                    jogadorLogado = null; // força a sair do menu e voltar ao login
                    while (jogadorLogado == null) {
                        System.out.println("\n--- Menu Inicial ---");
                        System.out.println("1 - Novo Jogador");
                        System.out.println("2 - Entrar com Jogador Existente");
                        System.out.print("Escolha uma opção: ");
                        int escolha = scanner.nextInt();
                        scanner.nextLine();

                        if (escolha == 1) {
                            System.out.print("Digite seu nome de jogador: ");
                            String nome = scanner.nextLine();

                            if (banco.nomeExiste(nome)) {
                                System.out.println("❌ Nome já está em uso.");
                                continue;
                            }

                            System.out.print("Digite sua senha: ");
                            String senha = scanner.nextLine();
                            Jogador novo = new Jogador(1, nome, senha, 100, null);
                            banco.adicionarJogador(novo);
                            System.out.println("✅ Jogador criado com sucesso! Faça login para continuar.");
                        } else if (escolha == 2) {
                            System.out.print("Usuário: ");
                            String login = scanner.nextLine();
                            System.out.print("Senha: ");
                            String tentativa = scanner.nextLine();

                            Jogador encontrado = banco.buscarPorLogin(login, tentativa);
                            if (encontrado != null) {
                                jogadorLogado = encontrado;
                                System.out.println("✅ Login bem-sucedido!");
                            } else {
                                System.out.println("❌ Usuário ou senha incorretos.");
                            }
                        } else {
                            System.out.println("❌ Opção inválida.");
                        }
                    }
                    break;


                default:
                    System.out.println("❌ Opção inválida.");
            }
        } while (opcao != 7);
    }
}
