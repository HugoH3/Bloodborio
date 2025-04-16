package ItemType;

import Entities.Personagem;

public class Item {
    private String nome;
    private String tipo;
    private int valor;

    public Item(String nome, String tipo, int valor) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }
    public Item(){}

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void usar(Personagem p) {
        switch (tipo) {
            case "cura":
                p.curar(valor);
                System.out.println("✨ " + nome + " curou " + valor + " de vida.");
                break;
            case "mana":
                p.recuperarMana(valor);
                System.out.println("🔮 " + nome + " restaurou " + valor + " de mana.");
                break;
            case "vigor":
                p.recuperarVigor(valor);
                System.out.println("💪 " + nome + " restaurou " + valor + " de vigor.");
                break;
            default:
                System.out.println("❓ Tipo de item desconhecido.");
        }
    }
}
