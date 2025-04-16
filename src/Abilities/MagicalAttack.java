package Abilities;

import Entities.Personagem;

public class MagicalAttack extends Ability {


    public MagicalAttack(String name, int damage, int cure, int mana) {
        super(name, damage, cure, mana);
    }


    public String getName(String name){ return name;}
}
