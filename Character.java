// Abstract class representing a character in the battle simulation
public abstract class Character {
    protected int health;
    protected int attackPower;

    // Initializes health and attack power
    public Character(int health, int attackPower) {
        this.health = health;
        this.attackPower = attackPower;
    }

    // Method to attack another character
    public void attack(Character target) {
        if (isAlive()) {
            target.health -= this.attackPower;
        }
    }

    // Method to check if the character is still alive
    public boolean isAlive() {
        return this.health > 0;
    }
}
