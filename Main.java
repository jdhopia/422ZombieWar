public class Main {
    public static void main(String[] args) {
        SimulationController controller = new SimulationController();
        controller.initializeCharacters(17, 8);
        System.out.println("We have 17 survivors trying to make it to safety.");
        System.out.println("But there are 8 zombies waiting for them.");
        controller.printInitialStatus();
        controller.runSimulation();
        controller.reportResults();
    }
}
