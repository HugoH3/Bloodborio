package ItemType;

import Abilities.Ability;
import Entities.Personagem;

public class PergaminhoDeMagia extends Item {
    private Ability magia;

    public PergaminhoDeMagia(String nome, Ability magia) {
        super(nome, "pergaminho", 0);
        this.magia = magia;
    }

    @Override
    public void usar(Personagem p) {
        p.adicionarHabilidade(magia);
        System.out.println("📖 " + p.getNome() + " aprendeu a magia: " + magia.getName() + "!");
    }
}

