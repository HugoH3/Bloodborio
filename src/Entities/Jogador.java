package Entities;

import BackupJson.BackupJogadoresJson;
import DataStructure.Lista;

import java.util.Scanner;
import DataStructure.Node;
import  Abilities.MagicalAttack;
import  Abilities.MeleeAttack;

public class Jogador {
    private int idJogador;
    private String nome;
    private String senha;
    private int saldoMoedas;
    private Lista<Personagem> personagens;
    Scanner sc = new Scanner(System.in);

    public Jogador() {

    }

    public Jogador(int idJogador, String nome, String senha, int saldoMoedas, Lista<Personagem> personagens) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.senha = senha;
        this.saldoMoedas = saldoMoedas;
        this.personagens = new Lista<>();
    }



    public boolean autenticar(String nome, String senha) {

        return this.nome.equals(nome) && this.senha.equals(senha);
    }

    public void criarPersonagem(String nomeP1, ListaDeJogadores banco) {
        Personagem personagem = new Personagem(null, 20, 20,20,20,1,nomeP1,1,0,0);
        personagens.inserirTail(personagem); // ✅ só insere uma vez

        MeleeAttack ataqueFisico = new MeleeAttack("Espadada", 10, 0, 0);
        personagem.adicionarHabilidade(ataqueFisico);

        System.out.println("\nO que te fascina mais? " + personagem.getNome() + ":");
        System.out.println("1 - Fogo");
        System.out.println("2 - Escuro");
        System.out.println("3 - Mentes");
        System.out.print("> ");
        int escolha = sc.nextInt();
        sc.nextLine();

        MagicalAttack magiaEscolhida;
        switch (escolha) {
            case 1:
                magiaEscolhida = new MagicalAttack("Espirto da Chama", 20, 0, 5);
                break;
            case 2:
                magiaEscolhida = new MagicalAttack("Lança Afelia", 18, 0,3);
                break;
            case 3:
                magiaEscolhida = new MagicalAttack("Condução Insana", 12, 12, 3);
                break;
            default:
                System.out.println("⚠ Escolha inválida. Atribuindo 'Espirito da Chama' por padrão.");
                magiaEscolhida = new MagicalAttack("Espirito da Chama", 20, 0, 5);
        }

        personagem.adicionarHabilidade(magiaEscolhida);
        BackupJogadoresJson.salvarJogadores(banco);

        System.out.println("✅ Personagem " + personagem.getNome() + " criado com sucesso!");
        System.out.println("🔹 Habilidade física: Espadada");
        System.out.println("🔹 Magia inicial: " + magiaEscolhida.getName());
    }

    public Personagem selecionarPersonagemPorScanner(Scanner scanner) {
        if (personagens.isEmpty()) {
            System.out.println("⚠ Nenhum personagem disponível.");
            return null;
        }

        System.out.println("\n=== Seus Personagens ===");
        Node<Personagem> atual = personagens.getHead();
        int i = 1;
        while (atual != null) {
            System.out.println(i + " - " + atual.getData().getNome());
            atual = atual.getNext();
            i++;
        }

        System.out.print("Escolha pelo número: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        atual = personagens.getHead();
        for (int j = 1; atual != null && j < escolha; j++) {
            atual = atual.getNext();
        }

        if (atual != null) {
            return atual.getData();
        } else {
            System.out.println("❌ Personagem inválido.");
            return null;
        }
    }

    public void removerPersonagem(Personagem alvo) {
        Node<Personagem> atual = personagens.getHead();
        Node<Personagem> anterior = null;

        while (atual != null) {
            if (atual.getData().equals(alvo)) {
                if (anterior == null) {
                    personagens.removerHead();
                } else {
                    anterior.setNext(atual.getNext());
                }
                System.out.println("❌ Personagem " + alvo.getNome() + " foi brutalizado.");
                return;
            }
            anterior = atual;
            atual = atual.getNext();
        }
    }
    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getSaldoMoedas() {
        return saldoMoedas;
    }

    public void setSaldoMoedas(int saldoMoedas) {
        this.saldoMoedas = saldoMoedas;
    }

    public Lista getPersonagens() {
        return personagens;
    }

    public void setPersonagens(Lista personagens) {
        this.personagens = personagens;
    }
}
