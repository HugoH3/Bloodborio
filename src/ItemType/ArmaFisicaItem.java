package ItemType;

import Abilities.MeleeAttack;
import Entities.Personagem;

public class ArmaFisicaItem extends Item {
    private MeleeAttack habilidade;

    public ArmaFisicaItem(String nome, MeleeAttack habilidade) {
        super(nome, "arma", 0);
        this.habilidade = habilidade;
    }

    @Override
    public void usar(Personagem p) {
        p.adicionarHabilidade(habilidade);
        System.out.println("⚔️ " + p.getNome() + " aprendeu o ataque: " + habilidade.getName() + "!");
    }
}

