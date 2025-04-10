public class Jogador {
    private int idJogador;
    private String nome;
    private String senha;
    private int saldoMoedas;
    private Lista<Personagem> personagens;

    public Jogador(int idJogador, String nome, String senha, int saldoMoedas, Lista<Personagem> personagens) {
        this.idJogador = idJogador;
        this.nome = nome;
        this.senha = senha;
        this.saldoMoedas = saldoMoedas;
        this.personagens = personagens;
    }

    public boolean autenticar(String nome, String senha) {
        return this.nome.equals(nome) && this.senha.equals(senha);
    }

}
