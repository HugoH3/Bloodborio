import DataStructure.Lista;
import Entities.Personagem;

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

    public void criarPersonagem() {
        Personagem personagem = new Personagem(null, 20, 20,20,20,1,null,1,0,0);
        personagens.inserirHead(personagem);
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
