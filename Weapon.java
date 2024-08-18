import java.util.Random;

public class Weapon {
    private String name;
    private int damage;
    private double accuracy;

    public Weapon(String name, int damage, double accuracy) {
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public boolean hitsTarget() {
        Random random = new Random();
        return random.nextDouble() <= accuracy;
    }
}
