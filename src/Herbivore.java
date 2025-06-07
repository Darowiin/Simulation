import java.util.Queue;

public class Herbivore extends Creature {

    public Herbivore(Point location, int speed, int hp) {
        super(location, speed, hp);
        this.icon = Icons.HERBIVORE;
    }

    @Override
    public String toString() {
        return icon +"{" +
                "location=" + getLocation() +
                "speed=" + speed +
                ", hp=" + hp +
                '}';
    }

    @Override
    public void makeMove(Actions actions) {
        SimulationMap gameMap = actions.getMap();
        Queue<Point> path = actions.bfs(this.getLocation());

        if (path.isEmpty() || (path.size() == 1 && path.peek().equals(this.getLocation())))
            return;

        if (!path.isEmpty() && path.peek().equals(this.getLocation())) {
            path.remove();
        }

        for (int i = 0; i < this.speed && !path.isEmpty(); i++) {
            Point next = path.remove();
            Point current = this.getLocation();

            Entity targetEntity = gameMap.getMap().get(next);
            if (targetEntity instanceof Grass) {
                gameMap.getMap().remove(current);
                this.setLocation(next);
                gameMap.getMap().put(next, this);
                break;
            }

            if (targetEntity == null) {
                gameMap.getMap().remove(current);
                this.setLocation(next);
                gameMap.getMap().put(next, this);
            }
        }
    }
}
