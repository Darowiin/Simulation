public abstract class Creature extends Entity {
    protected double speed;
    protected int hp;
    public static final int HPMAXVALUE = 100;
    public static final int HPMINVALUE = 0;
    public static final int SPEEDMAXVALUE = 5;
    public static final int SPEEDMINVALUE = 0;

    public Creature(Point location, int speed, int hp) {
        super(location);
        if (speed > SPEEDMINVALUE && speed <= SPEEDMAXVALUE)
            this.speed = speed;
        else
            this.speed = 3;
        if (hp > HPMINVALUE && hp <= HPMAXVALUE)
            this.hp = hp;
        else
            this.hp = 100;
    }

    public abstract void makeMove(Actions actions);
}
