package Abilities;

public class MeleeAttack extends Ability {

    private int vigor;
    public MeleeAttack(String name, int damage, int cure, int vigor) {
        super(name, damage, cure);
        vigor = 0;
    }

}
