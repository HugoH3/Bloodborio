public class Personagem extends Entidade{

    private int xpAtual;
    private int xpTotal;

    public Personagem(Lista<Ability> habilidades, int manaAtual, int manaMaxima, int vidaAtual, int vidaMaxima, int nivel, String nome, int id, int xpAtual, int xpTotal) {
        super(habilidades, manaAtual, manaMaxima, vidaAtual, vidaMaxima, nivel, nome, id);
        this.xpAtual = xpAtual;
        this.xpTotal = xpTotal;
    }

    public int getXpAtual() {
        return xpAtual;
    }

    public void setXpAtual(int xpAtual) {
        this.xpAtual = xpAtual;
    }

    public int getXpTotal() {
        return xpTotal;
    }

    public void setXpTotal(int xpTotal) {
        this.xpTotal = xpTotal;
    }


}
