import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Random;

// class to control the simulation of the zombie war
public class SimulationController {
    private List<Character> survivors;
    private List<Character> zombies;
    private Random random;

    // initializes the list of survivors and zombies
    public SimulationController() {
        survivors = new ArrayList<>();
        zombies = new ArrayList<>();
        random = new Random();
    }

    // method to initialize characters with random types
    public void initializeCharacters() {
        // Randomize the number of each type of survivor and zombie
        int numScientists = random.nextInt(5);
        int numCivilians = random.nextInt(5);
        int numSoldiers = random.nextInt(5);
        int numCommonInfected = random.nextInt(5);
        int numTanks = random.nextInt(5);

        // Add survivors
        for (int i = 0; i < numScientists; i++) {
            survivors.add(new Scientist());
        }
        for (int i = 0; i < numCivilians; i++) {
            survivors.add(new Civilian());
        }
        for (int i = 0; i < numSoldiers; i++) {
            survivors.add(new Soldier());
        }

        // Add zombies
        for (int i = 0; i < numCommonInfected; i++) {
            zombies.add(new CommonInfected());
        }
        for (int i = 0; i < numTanks; i++) {
            zombies.add(new Tank());
        }
    }

    // method to run the simulation
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


    // method to report the results of the war
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
