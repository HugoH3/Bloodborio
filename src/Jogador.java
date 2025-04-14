import DataStructure.Lista;
import Entities.Personagem;
import java.util.Scanner;
import DataStructure.Node;

public class Jogador {
    private int idJogador;
    private String nome;
    private String senha;
    private int saldoMoedas;
    private Lista personagens;

    public Jogador(int idJogador, String nome, String senha, int saldoMoedas, Lista personagens) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.senha = senha;
        this.saldoMoedas = saldoMoedas;
        this.personagens = personagens;
    }

    public boolean autenticar(String nome, String senha) {

        return this.nome.equals(nome) && this.senha.equals(senha);
    }

    public void criarPersonagem(String nomeP1) {
        Personagem personagem = new Personagem(null, 20, 20,20,20,1,null,1,0,0);
        personagens.inserirHead(personagem);
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
