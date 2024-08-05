import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// class to control the simulation of the zombie war
public class SimulationController {
    private List<Character> survivors;
    private List<Character> zombies;

    // initializes the list of survivors and zombies
    public SimulationController() {
        survivors = new ArrayList<>();
        zombies = new ArrayList<>();
    }

    // method to initialize characters with random types
    public void initializeCharacters(int numSurvivors, int numZombies) {
        Random rand = new Random();
        for (int i = 0; i < numSurvivors; i++) {
            int survivorType = rand.nextInt(3);
            switch (survivorType) {
                case 0 -> survivors.add(new Soldier());
                case 1 -> survivors.add(new Civilian());
                case 2 -> survivors.add(new Scientist());
            }
        }
        for (int i = 0; i < numZombies; i++) {
            int zombieType = rand.nextInt(2);
            switch (zombieType) {
                case 0 -> zombies.add(new CommonInfected());
                case 1 -> zombies.add(new Tank());
            }
        }
    }

    // method to run the simulation
    public void runSimulation() {
        for (Character survivor : survivors) {
            for (Character zombie : zombies) {
                if (survivor.isAlive() && zombie.isAlive()) {
                    survivor.attack(zombie);
                }
            }
        }
        for (Character zombie : zombies) {
            for (Character survivor : survivors) {
                if (zombie.isAlive() && survivor.isAlive()) {
                    zombie.attack(survivor);
                }
            }
        }
    }

    // method to report the results of the war
    public void reportResults() {
        long survivorsAlive = survivors.stream().filter(Character::isAlive).count();
        System.out.println("Survivors alive: " + survivorsAlive);
    }
}
