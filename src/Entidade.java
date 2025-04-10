public abstract class Entidade {
    private int id;
    private String nome;
    private int nivel;
    private int vidaMaxima;
    private int vidaAtual;
    private int manaMaxima;
    private int manaAtual;

    private Lista<Ability> habilidades;

    public Entidade(Lista<Ability> habilidades, int manaAtual, int manaMaxima, int vidaAtual, int vidaMaxima, int nivel, String nome, int id) {
        this.habilidades = new Lista<>();
        this.manaAtual = manaAtual;
        this.manaMaxima = manaMaxima;
        this.vidaAtual = vidaAtual;
        this.vidaMaxima = vidaMaxima;
        this.nivel = nivel;
        this.nome = nome;
        this.id = id;
    }

    public void receberDanos(int dano) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) {
            this.vidaAtual = 0;
        }
    }

    public void curar(int valor) {
        if (this.vidaAtual > this.vidaMaxima) {
            this.vidaAtual = this.vidaMaxima;
        }
    }

    public boolean estaVivo(){
        return this.vidaAtual > 0;
    }

    public void subirNivel(){
        this.nivel++;
        this.vidaMaxima += 10;
        this.manaMaxima += 10;
        this.vidaAtual = this.vidaMaxima;
        this.manaAtual = this.manaMaxima;
        System.out.printf(nome + " Subiu para o nível " + nivel + "!");
    }

    public abstract void usarHabilidade(int idHabilidade, Entidade alvo);

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public Lista<Ability> getHabilidades() {
        return habilidades;
    }
}
