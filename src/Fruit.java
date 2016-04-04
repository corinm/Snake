import java.awt.*;
import java.util.Random;

public class Fruit extends GameObject {

    Handler handler;
    Random r;
    Point point;

    public Fruit(Handler handler) {

        this.id = ID.Fruit;
        this.handler = handler;

        r = new Random();

        generatePoint();

    }

    private Point getRandomPoint() {
        int randomX = r.nextInt(Game.GRID_WIDTH);
        int randomY = r.nextInt(Game.GRID_HEIGHT);
        return new Point(randomX, randomY);
    }

    protected void generatePoint() {
        Point randomPoint = getRandomPoint();

        Boolean okay = false;

        while (!okay) {
            randomPoint = getRandomPoint();

            if (this.handler.checkPoint(randomPoint) == ID.Empty) {
                okay = true;
            }
        }

        this.point = randomPoint;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Colors.FRUIT);
        // Render fruit
        int x = point.x * Game.BOX_WIDTH;
        int y = point.y * Game.BOX_HEIGHT;
        g.fillOval(x, y + Game.GRID_YOFFSET, Game.BOX_WIDTH, Game.BOX_HEIGHT);
    }

    public Point getPoint() {
        return point;
    }
}
