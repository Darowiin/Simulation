public class Renderer {
    public void render(SimulationMap map) {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                var obj = map.getMap().get(new Point(x, y));
                if (obj != null) {
                    System.out.printf("%-3s", obj.getIcon());
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println(); // Пустая строка после карты
    }
}