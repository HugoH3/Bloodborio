package Entities;

import DataStructure.Lista;
import DataStructure.Node;

public class ListaDeJogadores {
    private Lista<Jogador> jogadores;

    public ListaDeJogadores() {
        this.jogadores = new Lista<>();
    }

    public void adicionarJogador(Jogador jogador) {
        jogadores.inserirTail(jogador); // adiciona ao final da lista
    }
    public Lista<Jogador> getJogadores() {
        return jogadores;
    }
    public Jogador buscarPorLogin(String nome, String senha) {
        Node<Jogador> atual = jogadores.getHead();
        while (atual != null) {
            Jogador j = atual.getData();
            if (j.autenticar(nome, senha)) {
                return j;
            }
            atual = atual.getNext();
        }
        return null; // não encontrado
    }

    public boolean nomeExiste(String nome) {
        Node<Jogador> atual = jogadores.getHead();
        while (atual != null) {
            if (atual.getData().getNome().equalsIgnoreCase(nome)) {
                return true;
            }
            atual = atual.getNext();
        }
        return false;
    }

    public void exibirTodos() {
        Node<Jogador> atual = jogadores.getHead();
        int i = 1;
        while (atual != null) {
            System.out.println(i + " - " + atual.getData().getNome());
            atual = atual.getNext();
            i++;
        }
    }

    public boolean isEmpty() {
        return jogadores.isEmpty();
    }
}

