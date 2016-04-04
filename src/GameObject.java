import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {

    protected LinkedList<Point> points;
    protected ID id;

    public GameObject() {

    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public ID getId() {
        return this.id;
    }

    public LinkedList<Point> getPoints() {
        return this.points;
    }

}
