import java.util.Random;

// Abstract class survivor extended from character
public abstract class Survivor extends Character {
    protected Weapon weapon;

    public Survivor(int health, int attack) {
        super(health, attack);
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void attack(Character target) {
        if (isAlive() && weapon != null && weapon.hitsTarget()) {
            target.health -= weapon.getDamage();
            if (!target.isAlive()) {
                System.out.println(this.getClass().getSimpleName() + " with " + weapon.getName() + " killed " + target.getClass().getSimpleName());
            }
        }
    }
}
