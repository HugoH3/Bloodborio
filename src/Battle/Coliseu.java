package Battle;

import Entities.Personagem;
import DataStructure.Fila;
import DataStructure.Lista;
import DataStructure.Pilha;
import DataStructure.Node;

import java.util.Scanner;

public class Coliseu {
    public static void iniciarPvP(Lista<Personagem> personagens, Scanner scanner) {
        if (personagens == null || personagens.getHead() == null || personagens.getHead().getNext() == null) {
            System.out.println("⚠ É necessário pelo menos 2 personagens para iniciar o Coliseu.");
            return;
        }

        System.out.println("\n🏟️ Batalha no Coliseu");
        System.out.println("Escolha os personagens para o duelo:");

        Node<Personagem> atual = personagens.getHead();
        int i = 1;
        while (atual != null) {
            Personagem p = atual.getData();
            System.out.println(i + " - " + p.getNome() + " (Nível " + p.getNivel() + ")");
            atual = atual.getNext();
            i++;
        }

        System.out.print("Jogador 1, escolha seu personagem: ");
        int escolha1 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Jogador 2, escolha seu personagem: ");
        int escolha2 = scanner.nextInt();
        scanner.nextLine();

        if (escolha1 == escolha2) {
            System.out.println("❌ Os jogadores devem escolher personagens diferentes!");
            return;
        }

        Personagem p1 = getPersonagemPorIndice(personagens, escolha1);
        Personagem p2 = getPersonagemPorIndice(personagens, escolha2);

        if (p1 == null || p2 == null) {
            System.out.println("❌ Escolha inválida.");
            return;
        }

        Lista<Entities.Entidade> lista = new Lista<>();
        Fila<Entities.Entidade> fila = new Fila<>();
        Pilha<Entities.Entidade> pilha = new Pilha<>(30);
        Arena arena = new Arena(99, lista, fila, pilha);

        arena.adicionarParticipante(p1);
        arena.adicionarParticipante(p2);
        arena.iniciarBatalha();

        while (p1.estaVivo() && p2.estaVivo()) {
            arena.executarTurno();
        }
    }

    private static Personagem getPersonagemPorIndice(Lista<Personagem> lista, int index) {
        Node<Personagem> atual = lista.getHead();
        for (int i = 1; atual != null && i < index; i++) {
            atual = atual.getNext();
        }
        return (atual != null) ? atual.getData() : null;
    }
}

