import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationController {
    private List<Character> survivors;
    private List<Character> zombies;
    private List<Weapon> weaponCache;
    private Random random;

    public SimulationController() {
        survivors = new ArrayList<>();
        zombies = new ArrayList<>();
        weaponCache = new ArrayList<>();
        random = new Random();
        initializeWeaponCache();
    }

    private void initializeWeaponCache() {
        weaponCache.add(new Weapon("Shotgun", 25, 0.7));
        weaponCache.add(new Weapon("Submachine Gun", 15, 0.8));
        weaponCache.add(new Weapon("Assault Rifle", 20, 0.9));
        weaponCache.add(new Weapon("Pistol", 10, 0.95));
        weaponCache.add(new Weapon("Axe", 30, 0.6));
        weaponCache.add(new Weapon("Crowbar", 20, 0.75));
        weaponCache.add(new Weapon("Frying Pan", 5, 0.85));
    }

    public void initializeCharacters() {
        int numScientists = random.nextInt(5);
        int numCivilians = random.nextInt(5);
        int numSoldiers = random.nextInt(5);
        int numCommonInfected = random.nextInt(5);
        int numTanks = random.nextInt(5);

        for (int i = 0; i < numScientists; i++) {
            Scientist scientist = new Scientist();
            scientist.equipWeapon(getRandomWeapon());
            survivors.add(scientist);
        }
        for (int i = 0; i < numCivilians; i++) {
            Civilian civilian = new Civilian();
            civilian.equipWeapon(getRandomWeapon());
            survivors.add(civilian);
        }
        for (int i = 0; i < numSoldiers; i++) {
            Soldier soldier = new Soldier();
            soldier.equipWeapon(getRandomWeapon());
            survivors.add(soldier);
        }

        for (int i = 0; i < numCommonInfected; i++) {
            zombies.add(new CommonInfected());
        }
        for (int i = 0; i < numTanks; i++) {
            zombies.add(new Tank());
        }
    }

    private Weapon getRandomWeapon() {
        return weaponCache.get(random.nextInt(weaponCache.size()));
    }

    public void runSimulation() {
        int survivorCounter = 0;
        int zombieCounter = 0;

        // Survivors attack zombies
        for (Character survivor : survivors) {
            zombieCounter = 0;
            for (Character zombie : zombies) {
                if (survivor.isAlive() && zombie.isAlive()) {
                    survivor.attack(zombie);
                    if (!zombie.isAlive()) {
                        System.out.println(survivor.getClass().getSimpleName() + " " + survivorCounter + " killed " + zombie.getClass().getSimpleName() + " " + zombieCounter);
                    }
                }
                zombieCounter++;
            }
            survivorCounter++;
        }

        survivorCounter = 0;
        zombieCounter = 0;

        // Zombies attack survivors
        for (Character zombie : zombies) {
            survivorCounter = 0;
            for (Character survivor : survivors) {
                if (zombie.isAlive() && survivor.isAlive()) {
                    zombie.attack(survivor);
                    if (!survivor.isAlive()) {
                        System.out.println(zombie.getClass().getSimpleName() + " " + zombieCounter + " killed " + survivor.getClass().getSimpleName() + " " + survivorCounter);
                    }
                }
                survivorCounter++;
            }
            zombieCounter++;
        }
    }

    public void reportResults() {
        long survivorsAlive = survivors.stream().filter(Character::isAlive).count();
        if (survivorsAlive == 0) {
            System.out.println("None of the survivors made it.");
        } else {
            System.out.println("It seems " + survivorsAlive + " have made it to safety.");
        }
    }

    public void printInitialStatus() {
        long numScientists = survivors.stream().filter(s -> s instanceof Scientist).count();
        long numCivilians = survivors.stream().filter(s -> s instanceof Civilian).count();
        long numSoldiers = survivors.stream().filter(s -> s instanceof Soldier).count();
        long numCommonInfected = zombies.stream().filter(z -> z instanceof CommonInfected).count();
        long numTanks = zombies.stream().filter(z -> z instanceof Tank).count();

        System.out.println("We have " + survivors.size() + " survivors trying to make it to safety (" +
                numScientists + " scientist, " + numCivilians + " civilian, " + numSoldiers + " soldiers)");
        System.out.println("But there are " + zombies.size() + " zombies waiting for them (" +
                numCommonInfected + " common infected, " + numTanks + " tanks)");
    }
}