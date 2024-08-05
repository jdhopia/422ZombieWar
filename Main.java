public class Main {
    public static void main(String[] args) {
        SimulationController controller = new SimulationController();
        controller.initializeCharacters();
        controller.printInitialStatus();
        controller.runSimulation();
        controller.reportResults();
    }
}
