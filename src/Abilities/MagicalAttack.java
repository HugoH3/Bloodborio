package Abilities;

public class MagicalAttack extends Ability {

    private int mana;
    

    public MagicalAttack(String name, int damage, int cure, int mana) {
        super(name, damage, cure);
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
