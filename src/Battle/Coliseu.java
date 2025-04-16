package Battle;

import DataStructure.Fila;
import DataStructure.Lista;
import DataStructure.Node;
import DataStructure.Pilha;
import Entities.Entidade;
import Entities.Jogador;
import Entities.ListaDeJogadores;
import Entities.Personagem;

import java.util.Scanner;

public class Coliseu {

    public static void iniciarPvPDoisJogadores(ListaDeJogadores banco, Scanner scanner) {
        if (banco == null || banco.isEmpty()) {
            System.out.println("⚠ Nenhum jogador disponível.");
            return;
        }

        // Escolher Jogador 1
        Jogador jogador1 = selecionarJogador(banco, scanner, null);
        if (jogador1 == null) return;

        // Escolher Personagem 1
        Personagem p1 = jogador1.selecionarPersonagemPorScanner(scanner);
        if (p1 == null) return;

        // Escolher Jogador 2 (diferente do primeiro)
        Jogador jogador2 = selecionarJogador(banco, scanner, jogador1);
        if (jogador2 == null) return;

        // Escolher Personagem 2
        Personagem p2 = jogador2.selecionarPersonagemPorScanner(scanner);
        if (p2 == null) return;

        // Criar arena e iniciar batalha
        Lista<Entidade> lista = new Lista<>();
        Fila<Entidade> fila = new Fila<>();
        Pilha<Entidade> pilha = new Pilha<>(30);

        Arena arena = new Arena(999, lista, fila, pilha);
        arena.adicionarParticipante(p1);
        arena.adicionarParticipante(p2);
        arena.iniciarBatalha();

        while (p1.estaVivo() && p2.estaVivo()) {
            arena.executarTurno();
        }
    }

    private static Jogador selecionarJogador(ListaDeJogadores banco, Scanner scanner, Jogador excluido) {
        System.out.println("\n=== Jogadores Disponíveis ===");
        banco.exibirTodos();
        System.out.print("Digite o nome do jogador: ");
        String nome = scanner.nextLine();

        Node<Jogador> atual = banco.getJogadores().getHead();
        while (atual != null) {
            Jogador j = atual.getData();
            if (j.getNome().equalsIgnoreCase(nome)) {
                if (excluido != null && j.equals(excluido)) {
                    System.out.println("❌ O mesmo jogador não pode lutar contra si mesmo.");
                    return null;
                }
                return j;
            }
            atual = atual.getNext();
        }

        System.out.println("❌ Jogador não encontrado.");
        return null;
    }
}
