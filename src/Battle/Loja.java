package Battle;
import Entities.Jogador;
import Entities.Personagem;
import ItemType.Item;

import java.util.Scanner;

    public class Loja {
        public static void abrirLoja(Jogador jogador, Scanner scanner) {
            System.out.println("\n🛒 Bem-vindo à Loja do Aventureiro!");
            Personagem comprador = jogador.selecionarPersonagemPorScanner(scanner);
            if (comprador == null) return;

            boolean comprando = true;
            while (comprando) {
                System.out.println("\nSeu saldo: " + jogador.getSaldoMoedas() + " moedas");
                System.out.println("1 - Poção de Vida (30 moedas)");
                System.out.println("2 - Poção de Mana (25 moedas)");
                System.out.println("3 - Poção de Vigor (20 moedas)");
                System.out.println("4 - Sair da loja");
                System.out.print("> ");
                int escolha = scanner.nextInt();
                scanner.nextLine();

                Item itemComprado = null;
                int preco = 0;

                switch (escolha) {
                    case 1 -> {
                        itemComprado = new Item("Poção de Vida", "cura", 30);
                        preco = 30;
                    }
                    case 2 -> {
                        itemComprado = new Item("Poção de Mana", "mana", 20);
                        preco = 25;
                    }
                    case 3 -> {
                        itemComprado = new Item("Poção de Vigor", "vigor", 25);
                        preco = 20;
                    }
                    case 4 -> {
                        comprando = false;
                        continue;
                    }
                    default -> System.out.println("❌ Opção inválida.");
                }

                if (itemComprado != null) {
                    if (jogador.getSaldoMoedas() >= preco) {
                        jogador.setSaldoMoedas(jogador.getSaldoMoedas() - preco);
                        comprador.adicionarItem(itemComprado);
                        System.out.println("🧾 Você comprou: " + itemComprado.getNome());
                    } else {
                        System.out.println("⚠ Saldo insuficiente.");
                    }
                }
            }
        }
    }


