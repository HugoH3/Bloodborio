package Entities;

import Abilities.Ability;
import Abilities.MeleeAttack;
import Abilities.MagicalAttack;
import DataStructure.Lista;
import DataStructure.Node;
import ItemType.Item;

import java.util.Scanner;


public class Personagem extends Entidade {
    private int xpAtual;
    private int xpTotal;
    private int vigor;
    private Lista<Item> inventario;

    public Personagem(Lista<Ability> habilidades, int manaAtual, int manaMaxima, int vidaAtual, int vidaMaxima, int nivel, String nome, int id, int xpAtual, int xpTotal) {
        super(habilidades, manaAtual, manaMaxima, vidaAtual, vidaMaxima, nivel, nome, id);
        this.xpAtual = xpAtual;
        this.xpTotal = xpTotal;
        this.vigor = 50;
        this.inventario = new Lista<>();
    }

    public void usarHabilidade(Entidade alvo, int opcao) {
        if (habilidades == null || habilidades.getHead() == null) {
            System.out.println("⚠ Nenhuma habilidade disponível!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n🔹 Status de " + getNome() + ":");
        System.out.println("Vida: " + getVidaAtual() + " | Mana: " + getManaAtual() + " | Vigor: " + getVigor());

        System.out.println("Escolha o tipo de habilidade:");
        System.out.println("1 - Físicas");
        System.out.println("2 - Mágicas");
        int tipo = scanner.nextInt();

        Node<Ability> atual = habilidades.getHead();
        Lista<Ability> listaExibida = new Lista<>();
        int i = 1;

        while (atual != null) {
            Ability hab = atual.getData();
            if ((tipo == 1 && hab instanceof MeleeAttack) || (tipo == 2 && hab instanceof MagicalAttack)) {
                System.out.println(i + " - " + hab.getName() + " (Dano: " + hab.getDamage() + " | Custo: " + hab.getCusto() + ")");
                listaExibida.inserirTail(hab);
                i++;
            }
            atual = atual.getNext();
        }

        if (listaExibida.getHead() == null) {
            System.out.println("⚠ Nenhuma habilidade desse tipo.");
            return;
        }

        System.out.print("Escolha a habilidade: ");
        int escolha = scanner.nextInt();
        Node<Ability> nodeEscolhido = listaExibida.getHead();
        for (int j = 1; j < escolha && nodeEscolhido != null; j++) {
            nodeEscolhido = nodeEscolhido.getNext();
        }

        if (nodeEscolhido == null) {
            System.out.println("❌ Habilidade inválida.");
            return;
        }

        Ability habilidade = nodeEscolhido.getData();

        if (habilidade instanceof MeleeAttack) {
            if (vigor >= habilidade.getCusto()) {
                alvo.receberDanos(habilidade.getDamage());
                vigor -= habilidade.getCusto();
                System.out.println(getNome() + " usou " + habilidade.getName() + " causando " + habilidade.getDamage() + " de dano!");
            } else {
                System.out.println("⚠ Vigor insuficiente.");
            }
        } else if (habilidade instanceof MagicalAttack) {
            if (manaAtual >= habilidade.getCusto()) {
                alvo.receberDanos(habilidade.getDamage());
                manaAtual -= habilidade.getCusto();
                System.out.println(getNome() + " lançou " + habilidade.getName() + " causando " + habilidade.getDamage() + " de dano!");
            } else {
                System.out.println("⚠ Mana insuficiente.");
            }
        }
    }

    public void adicionarItem(Item item) {
        inventario.inserirHead(item);
    }

    public void usarItem(Scanner scanner) {
        if (inventario.getHead() == null) {
            System.out.println("📦 Inventário vazio.");
            return;
        }

        Node<Item> atual = inventario.getHead();
        int i = 1;
        while (atual != null) {
            Item item = atual.getData();
            System.out.println(i + " - " + item.getNome() + " (" + item.getTipo() + " +" + item.getValor() + ")");
            atual = atual.getNext();
            i++;
        }

        System.out.print("Escolha o item: ");
        int escolha = scanner.nextInt();
        Node<Item> anterior = null;
        atual = inventario.getHead();
        for (int j = 1; j < escolha && atual != null; j++) {
            anterior = atual;
            atual = atual.getNext();
        }

        if (atual != null) {
            atual.getData().usar(this);
            if (anterior == null) {
                inventario.removerHead(); // ✅ remove o primeiro item da lista
            } else {
                anterior.setNext(atual.getNext()); // ✅ remove do meio/final
            }
        } else {
            System.out.println("❌ Item inválido.");
        }


    }

    public void ganharExperiencia(int xp) {
        this.xpAtual += xp;
        if (xpAtual >= xpTotal) {
            subirNivel();
        }
    }

    public void subirNivel() {
        nivel++;
        xpAtual -= xpTotal;
        xpTotal += 25 + (nivel * 5);
        vidaMaxima += 10;
        manaMaxima += 5;
        vigor += 5;
        vidaAtual = vidaMaxima;
        manaAtual = manaMaxima;
        System.out.println("✨ " + nome + " subiu para o nível " + nivel + "!");
    }

    public void recuperarVidaTotal() {
        vidaAtual = vidaMaxima;
    }

    public void recuperarMana(int valor) {
        manaAtual = Math.min(manaAtual + valor, manaMaxima);
    }

    public void recuperarVigor(int valor) {
        vigor += valor;
    }

    public void recuperarManaTotal() {
        manaAtual = manaMaxima;
    }

    public void recuperarVigorTotal() {
        vigor = 50;
    }

    public void curar(int valor) {
        vidaAtual = Math.min(vidaAtual + valor, vidaMaxima);
    }

    public int getXpAtual() { return xpAtual; }
    public void setXpAtual(int xpAtual) { this.xpAtual = xpAtual; }
    public int getXpTotal() { return xpTotal; }
    public void setXpTotal(int xpTotal) { this.xpTotal = xpTotal; }
    public int getVigor() { return vigor; }
}