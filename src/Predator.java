import java.util.Queue;

public class Predator extends Creature {
    private final int strength;
    public static final int STRENGTHMAXVALUE = 30;
    public static final int STRENGTHMINVALUE = 0;

    public Predator(Point location, int speed, int hp, int strength) {
        super(location, speed, hp);
        if (strength > STRENGTHMINVALUE && strength <= STRENGTHMAXVALUE)
            this.strength = strength;
        else
            this.strength = 30;
        this.icon = Icons.PREDATOR;
    }

    @Override
    public String toString() {
        return icon +"{" +
                "location=" + getLocation() +
                "speed=" + speed +
                ", hp=" + hp +
                ", strength=" + strength +
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

            if (targetEntity instanceof Herbivore prey) {
                if (this.strength >= prey.hp) {
                    gameMap.getMap().remove(current);
                    this.setLocation(next);
                    gameMap.getMap().put(next, this);
                } else {
                    prey.hp -= this.strength;

                }
                break;
            }

            else if (targetEntity == null) {
                gameMap.getMap().remove(current);
                this.setLocation(next);
                gameMap.getMap().put(next, this);
            }
            else {
                break;
            }
        }
    }
}
