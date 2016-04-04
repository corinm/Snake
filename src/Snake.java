import java.awt.*;
import java.util.LinkedList;

public class Snake extends GameObject {

    Handler handler;
    ScoreKeeper sk;

    public int direction;
    private LinkedList<Point> points;
    private int x;
    private int y;

    public Snake(Handler handler, ScoreKeeper sk) {
        this.handler = handler;
        this.sk = sk;
        this.id = ID.Snake;

        this.direction = Direction.NO_DIRECTION;

        this.points = new LinkedList<>();

        this.x = Game.GRID_WIDTH / 2;
        this.y = Game.GRID_HEIGHT / 2;

        handler.clearPoints();
        addPoint(new Point(x,y));
        addPoint(new Point(x,y-1));
        addPoint(new Point(x,y-2));

    }

    public void addPoint(Point p) {

        points.addFirst(p);
        handler.addPoint(p, this);

    }

    // Reverses snake if player presses down at start
    public void reverseSnake() {

        points.clear();

        points.add(new Point(x,y));
        points.add(new Point(x,y-1));
        points.add(new Point(x,y-2));

    }

    public void removeTail() {

        // Get tail, remove from snake object, remove from handler allPoints dictionary
        Point tail = points.peekLast();
        points.remove(points.peekLast());
        handler.removePoint(tail);

    }

    public void move() {

        // Peek first gets first point in linked list
        Point currentHead = points.peekFirst();
        Point newPoint = currentHead;

        switch (direction) {
            case Direction.NO_DIRECTION:
                return;
            case Direction.UP:
                newPoint = new Point(currentHead.x, currentHead.y - 1);
                break;
            case Direction.DOWN:
                newPoint = new Point(currentHead.x, currentHead.y + 1);
                break;
            case Direction.LEFT:
                newPoint = new Point(currentHead.x - 1, currentHead.y);
                break;
            case Direction.RIGHT:
                newPoint = new Point(currentHead.x + 1, currentHead.y);
                break;
        }

        // First, check if in bounds
        if(newPoint.x < 0 || newPoint.x >= (Game.GRID_WIDTH)) {
            handler.reset();
        } else if(newPoint.y < 0 || newPoint.y >= (Game.GRID_HEIGHT)) {
            handler.reset();
        }

        // Check what the object is
        switch(handler.checkPoint(newPoint)) {
            case Empty:
                // Move snake (i.e. add head and remove tail)
                addPoint(newPoint);
                removeTail();
                break;

            case Fruit:
                // Increase snake (i.e. add head, don't remove tail)
                handler.removeFruit(newPoint);
                addPoint(newPoint);
                handler.addFruit();
                sk.incrementScore();
                return;

            case FruitRotten:
                // TODO: Fill out
                // Decrement score
                handler.removeFruit(newPoint);
                addPoint(newPoint);

                // Delete snakes tail - once because it's moving, once to shorten snake
                removeTail();
                removeTail();

                sk.decrementScore();

                break;

            case Snake:
            case Wall:
                // Reset game
                handler.reset();
                break;

            default:
                throw new IllegalArgumentException("Unexpected cell ID when checking new point in Snake.move()");
        }
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Colors.SNAKE);
        // Render snake
        for (Point p : points) {
            g.fillRect(p.x * Game.BOX_WIDTH, p.y * Game.BOX_HEIGHT + Game.GRID_YOFFSET, Game.BOX_WIDTH, Game.BOX_HEIGHT);
        }
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
