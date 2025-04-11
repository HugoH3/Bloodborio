public abstract class Ability {

    private String name;
    private int damage;
    private int cure;
    private int id;

    public Ability(String name, int damage, int cure) {
        this.name = name;
        this.damage = damage;
        this.cure = cure;
    }

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
}
