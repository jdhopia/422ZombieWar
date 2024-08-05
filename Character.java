// Abstract class representing a character in the battle simulation
public abstract class Character {
    protected int health;
    protected int attackPower;

    // initializes health and attack power
    public Character(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
    }

    // method to attack another character
    public void attack(Character target) {
        if (isAlive()) {
            target.health -= this.attackPower;
        }
    }


    // method to check if the player is still alive
    public boolean isAlive() {
        return this.health > 0;
    }
}
