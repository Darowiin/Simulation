import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Actions {
    private SimulationMap simulationMap;
    private final String[] entities = new String[]{
            "Herbivore",
            "Predator",
            "Grass",
            "Rock",
            "Tree"
    };
    private final Random rand = new Random();

    public SimulationMap initAction() {
        SimulationMap simulationMap = new SimulationMap();
        simulationMap.setHeight(10);
        simulationMap.setWidth(10);

        addEntitiesAction(simulationMap, 40, false);
        this.simulationMap = simulationMap;
        return simulationMap;
    }

    public void turnAction() {
        List<Entity> creatures = new ArrayList<>();
        for (Entity entity : this.simulationMap.getMap().values()) {
            if (entity instanceof Creature) {
                creatures.add(entity);
            }
        }
        for (Entity creature : creatures) {
            ((Creature) creature).makeMove(this);
        }
        addEntitiesAction(this.simulationMap, 2, creatures.size() < (simulationMap.getHeight() * simulationMap.getWidth()) / 2);
    }

    private void addEntitiesAction(SimulationMap simulationMap, int numberOfEntities, boolean createOnlyCreatures) {
        for (int i = 0; i < numberOfEntities; i++) {
            String className;
            if (createOnlyCreatures) {
                className = entities[rand.nextInt(entities.length-2)];
            } else
                className = entities[rand.nextInt(entities.length)];

            Point point = Point.getRandomPoint(rand, simulationMap.getHeight());
            if (i != 0) {
                do {
                    point.setX(rand.nextInt(simulationMap.getWidth()));
                    point.setY(rand.nextInt(simulationMap.getHeight()));
                } while (simulationMap.getMap().containsKey(point));
            }
            switch (className) {
                case "Grass":
                    Grass grass = new Grass(point);
                    simulationMap.getMap().put(point, grass);
                    break;
                case "Rock":
                    Rock rock = new Rock(point);
                    simulationMap.getMap().put(point, rock);
                    break;
                case "Tree":
                    Tree tree = new Tree(point);
                    simulationMap.getMap().put(point, tree);
                    break;
                case "Herbivore":
                    Herbivore herbivore = new Herbivore(point, rand.nextInt(Herbivore.SPEEDMAXVALUE), rand.nextInt(Herbivore.HPMAXVALUE));
                    simulationMap.getMap().put(point, herbivore);
                    break;
                case "Predator":
                    Predator predator = new Predator(point, rand.nextInt(Predator.SPEEDMAXVALUE), rand.nextInt(Predator.HPMAXVALUE), rand.nextInt(Predator.STRENGTHMAXVALUE));
                    simulationMap.getMap().put(point, predator);
                    break;
            }
        }
    }

    public Queue<Point> bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        HashSet<Point> visited = new HashSet<>();
        HashMap<Point, Point> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Point current = queue.remove();

            Entity currentEntity = simulationMap.getMap().get(current);
            Entity startEntity = simulationMap.getMap().get(start);

            if ((startEntity instanceof Herbivore && currentEntity instanceof Grass) ||
                    (startEntity instanceof Predator && currentEntity instanceof Herbivore)) {

                LinkedList<Point> path = new LinkedList<>();
                while (current != null) {
                    path.addFirst(current);
                    current = parent.get(current);
                }
                return path;
            }

            Point[] directions = {
                    new Point(current.getX() + 1, current.getY()),
                    new Point(current.getX(), current.getY() + 1),
                    new Point(current.getX() - 1, current.getY()),
                    new Point(current.getX(), current.getY() - 1)
            };

            for (Point next : directions) {
                if (isDirectionAvailable(simulationMap, visited, next)) {
                    queue.add(next);
                    visited.add(next);
                    parent.put(next, current);
                }
            }
        }

        return new LinkedList<>();
    }
    private boolean isDirectionAvailable(SimulationMap simulationMap, HashSet<Point> visited, Point direction) {
        return direction.getX() >= 0 && direction.getX() < simulationMap.getWidth() &&
                direction.getY() >= 0 && direction.getY() < simulationMap.getHeight() &&
                !visited.contains(direction) &&
                !(simulationMap.getMap().get(direction) instanceof Rock) &&
                !(simulationMap.getMap().get(direction) instanceof Tree);
    }
    public SimulationMap getMap() {
        return simulationMap;
    }
}
