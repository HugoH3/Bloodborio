package Abilities;

public class Ability {

    private String name;
    private int damage;
    private int cure;
    private int id;
    private int mana;

    public Ability(String name, int damage, int cure, int mana) {
        this.name = name;
        this.damage = damage;
        this.cure = cure;
        this.mana = mana;
    }
    public Ability() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCure() {
        return cure;
    }

    public void setCure(int cure) {
        this.cure = cure;
    }

    public int getCusto() {
        return cure;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
