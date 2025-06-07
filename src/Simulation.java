import java.util.Scanner;

public class Simulation {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startSimulation();
    }

    public static void startSimulation() {
        Actions actions = new Actions();
        SimulationMap simulationMap = actions.initAction();
        int turnCounter = 0;
        Renderer renderer = new Renderer();

        System.out.println("=== Начальное состояние ===");
        renderer.render(simulationMap);
        System.out.println("Нажмите Enter для следующего хода или 'q' для выхода...");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
            System.out.println("\n=== Ход " + (turnCounter + 1) + " ===");

            actions.turnAction();
            renderer.render(simulationMap);
            turnCounter++;
        }

    }
}