package Battle;
import Abilities.MagicalAttack;
import Abilities.MeleeAttack;
import DataStructure.Fila;
import DataStructure.Lista;
import DataStructure.Pilha;
import Entities.Entidade;
import Entities.Jogador;
import Entities.Monster;
import Entities.Personagem;
import ItemType.Item;

import java.util.Scanner;

public class FlorestaManager {

    public static void explorarFloresta(Personagem personagem, Jogador jogador, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n🌲 Você entra mais fundo na floresta...");

            Monster monstro = gerarMonstroAleatorio();
            System.out.println("⚔ Um " + monstro.getNome() + " apareceu!");

            Lista<Entidade> lista = new Lista<>();
            Fila<Entidade> fila = new Fila<>();
            Pilha<Entidade> pilha = new Pilha<>(30);
            Arena arena = new Arena(1, lista, fila, pilha);

            arena.adicionarParticipante(personagem);
            arena.adicionarParticipante(monstro);
            arena.iniciarBatalha();

            while (personagem.estaVivo() && monstro.estaVivo()) {
                arena.executarTurno();
            }

            if (!personagem.estaVivo()) {
                System.out.println("☠ Você foi derrotado... seu personagem foi removido.");
                jogador.removerPersonagem(personagem);
                return;
            }

            if (Math.random() < 0.7) {
                Item drop = gerarItemAleatorio();
                personagem.adicionarItem(drop);
                System.out.println("🎁 Você encontrou um item: " + drop.getNome());
            }

            personagem.ganharExperiencia(20);

            System.out.println("\nDeseja continuar na floresta?");
            System.out.println("1 - Continuar");
            System.out.println("2 - Sair");
            System.out.print("> ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 2) {
                continuar = false;
                System.out.println("🌿 Você saiu da floresta com vida.");
            }
        }
    }

    public static Monster gerarMonstroAleatorio() {
        int escolha = (int)(Math.random() * 3);
        Monster m;
        switch (escolha) {
            case 0:
                m = new Monster(null, 20, 20, 50, 10, 20, "Zumbi", 2, 10);
                m.adicionarHabilidade(new MeleeAttack("Garra", 8, 3, 3));
                break;
            case 1:
                m = new Monster(null, 0, 0, 60, 15, 20, "Esqueleto", 3 , 10);
                m.adicionarHabilidade(new MeleeAttack("Espadada Enferrujada", 12, 4, 2));
                break;
            case 2:
            default:
                m = new Monster(null, 12, 12, 40, 20, 15, "Slime",4 , 20);
                m.adicionarHabilidade(new MagicalAttack("Baba Ácida", 10, 5, 4));
                break;
        }
        return m;
    }

    public static Item gerarItemAleatorio() {
        int tipo = (int)(Math.random() * 3);
        switch (tipo) {
            case 0: return new Item("Poção de Vida", "cura", 30);
            case 1: return new Item("Poção de Mana", "mana", 20);
            case 2: return new Item("Poção de Vigor", "vigor", 25);
            default: return null;
        }
    }
}

