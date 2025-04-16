import BackupJson.BackupJogadoresJson;
import Battle.Coliseu;
import Battle.FlorestaManager;
import Battle.Loja;
import Entities.Jogador;
import Entities.ListaDeJogadores;
import Entities.Personagem;

import java.util.Scanner;

public class Main {

    public static void pausa(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDeJogadores banco = new ListaDeJogadores();
        Jogador jogadorLogado = null;



        System.out.println("\n\n🕯️...");
        pausa(1200);
        System.out.println("Voz antiga sussurra entre as sombras...\n");
        pausa(2000);

        System.out.println("\"Mais um...\"");
        pausa(1800);
        System.out.println("\"Outro tolo com esperança nas mãos e cinzas nos olhos.\"\n");
        pausa(2500);

        System.out.println("\"Você vem como os outros vieram. Com bravura. Com fé. Com a ilusão de que importa.\"");
        pausa(3000);
        System.out.println("\"Mas escute bem, Acendedor...\"");
        pausa(2200);

        System.out.println("\"A luz se foi. Ela nos abandonou, assim como abandonará você.\"\n");
        pausa(2800);

        System.out.println("\"Lian... aquela que era tudo, foi traída. Aprisionada. Corrompida. E vocês a chamam de salvação?\"");
        pausa(3000);

        System.out.println("\"Dormar a consome. A escuridão se alimenta de suas memórias.\"\n");
        pausa(2500);

        System.out.println("\"Mil pés já pisaram este caminho. Mil corpos jazem esquecidos.\"\n");
        pausa(2000);

        System.out.println("\"Você... não é especial.\"\n");
        pausa(1800);

        System.out.println("\"Mas vá. Acenda o que puder. Queime-se tentando.\"\n");
        pausa(2200);

        System.out.println("🌫️ A névoa se dissipa, e o mundo respira silêncio.\n\n");
        pausa(2500);

        // === MENU INICIAL ===
        while (jogadorLogado == null) {
            System.out.println("\n--- Menu Inicial ---");
            System.out.println("1 - Novo Jogador");
            System.out.println("2 - Entrar com Jogador Existente");
            System.out.println("3 - Backup Jogadores");
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
                BackupJogadoresJson.salvarJogadores(banco);
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
            } else if(escolha==3) {
                banco = BackupJogadoresJson.carregarJogadores();
                banco.exibirTodos();
            }

                else {
                System.out.println("❌ Opção inválida.");
            }
        }

        // === MENU PRINCIPAL ===
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Acender uma chama");
            System.out.println("2 - Ir para o coração de Dormar");
            System.out.println("3 - Descansar perto a fogueira");
            System.out.println("4 - Falar com o Visitante");
            System.out.println("5 - Bolsa");
            System.out.println("6 - Disciplina interior");
            System.out.println("7 - Sair");
            System.out.println("8 - Trocar de Conta");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n🌘 Você sente... algo despertar.");
                    pausa(1800);
                    System.out.println("Não é carne. Não é alma. É propósito.");
                    pausa(2000);

                    System.out.println("\"Um novo nome nasce entre as sombras...\"");
                    pausa(2200);
                    System.out.println("\"Outro Acendedor ergue sua tocha contra o vazio.\"\n");
                    pausa(2400);

                    System.out.println("\"Lembre-se: a luz que você carrega é tudo o que resta. Proteja-a. Corrompa-a. Ou... acenda o mundo novamente.\"");
                    pausa(3000);
                    System.out.print("Qual seu nome, Acendedor? : ");
                    String nomePers = scanner.nextLine();
                    jogadorLogado.criarPersonagem(nomePers,banco);
                    break;

                case 2:
                    System.out.println("\n🌑 O chão pulsa sob seus pés.");
                    pausa(1800);
                    System.out.println("Este não é apenas um bosque. É o coração que lateja por trás da noite.");
                    pausa(2200);
                    System.out.println("\"Dormar te observa. Dormar te lembra.\"");
                    pausa(2400);
                    System.out.println("\"E cada passo é uma batida mais perto do fim.\"");
                    pausa(2600);

                    Personagem explorador = jogadorLogado.selecionarPersonagemPorScanner(scanner);
                    if (explorador != null) {
                        FlorestaManager.explorarFloresta(explorador, jogadorLogado, scanner);
                    }
                    break;

                case 3:
                    System.out.println("\n🕯️ A fogueira dança, mas o calor não aquece.");
                    pausa(1800);
                    System.out.println("A brisa sussurra memórias que não são suas.");
                    pausa(2200);
                    System.out.println("\"Descansar... por ora. Mas não se esqueça: a noite não dorme.\"");
                    pausa(2500);

                    Personagem p = jogadorLogado.selecionarPersonagemPorScanner(scanner);
                    if (p != null) {
                        p.recuperarVidaTotal();
                        p.recuperarManaTotal();
                        p.recuperarVigorTotal();
                        System.out.println("Talvez seja perigoso continuar parado.");
                    }
                    break;

                case 4:
                    System.out.println("\n🌒 Você atravessa a névoa e encontra O Visitante.");
                    pausa(2000);
                    System.out.println("Ele não tem rosto, mas parece sorrir.");
                    pausa(1800);
                    System.out.println("\"Então você também veio buscar luz nas sobras, Acendedor?\"");
                    pausa(2400);
                    System.out.println("\"Eu vendo o que resta dos sonhos quebrados. Mas tudo tem um preço...\"");
                    pausa(2600);

                    Loja.abrirLoja(jogadorLogado, scanner);
                    break;

                case 5:
                    Personagem alvo = jogadorLogado.selecionarPersonagemPorScanner(scanner);
                    if (alvo != null) {
                        alvo.usarItem(scanner);
                    }
                    break;

                case 6:
                    System.out.println("\n🏛️ As portas se fecham atrás de você.");
                    pausa(1500);
                    System.out.println("Não há plateia. Apenas ecos da sua própria alma.");
                    pausa(2000);
                    System.out.println("\"Aqui não se enfrentam monstros...\"");
                    pausa(1800);
                    System.out.println("\"Aqui você enfrentará aquilo que você esconde de si mesmo.\"");
                    pausa(2600);

                    Coliseu.iniciarPvPDoisJogadores(banco, scanner);
                    break;

                case 7:
                    System.out.println("A chama se apaga...");
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
                                System.out.println("Nome já está em uso.");
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
