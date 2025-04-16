package Entities;

import Abilities.Ability;
import DataStructure.Lista;

public class Monster extends Entidade{

    private int xpDrop;

    public Monster(Lista<Ability> habilidades, int manaAtual, int manaMaxima, int vidaAtual, int vidaMaxima, int nivel, String nome, int id, int xpDrop) {
        super(habilidades, manaAtual, manaMaxima, vidaAtual, vidaMaxima, nivel, nome, id);
        this.xpDrop = xpDrop;
    }

    public int getXpDrop() {
        return xpDrop;
    }

    public void setXpDrop(int xpDrop) {
        this.xpDrop = xpDrop;
    }

    public void estarVivo(){}


}
