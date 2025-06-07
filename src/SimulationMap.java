import java.util.HashMap;

public class SimulationMap {
    private HashMap<Point, Entity> map;
    private int height;
    private int width;

    public SimulationMap() {
        this.map = new HashMap<>();
    }
    public void setHeight(int height) {
        if (height > 0)
            this.height = height;
    }
    public void setWidth(int width) {
        if (width > 0)
            this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public HashMap<Point, Entity> getMap() {
        return map;
    }
}
