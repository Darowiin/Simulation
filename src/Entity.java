public abstract class Entity {
    private Point location;
    protected String icon;

    public Entity(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
    public String getIcon() {
        return icon;
    }
    public void setLocation(Point location) {
        this.location = location;
    }
}
