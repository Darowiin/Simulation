public class Renderer {
    public void render(SimulationMap simulationMap) {
        for (int y = 0; y < simulationMap.getHeight(); y++) {
            for (int x = 0; x < simulationMap.getWidth(); x++) {
                var obj = simulationMap.getMap().get(new Point(x, y));
                String cell = (obj != null) ? "|" + obj.getIcon() + "|" : "|  |";
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
