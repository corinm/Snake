import java.awt.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Wall extends GameObject {

    Random r;
    private LinkedList<Point> points;
    private Handler handler;

    //private final LinkedList<Point> WALL1 = generateWall( 5, 5, 5,29);
    private final LinkedList<Point> WALL2 = generateWall(10,10,24,10);

    public Wall(Handler handler, Snake snake) {

        this.id = ID.Wall;
        this.points = new LinkedList<>();
        this.handler = handler;

        Boolean okay = false;

        while (!okay) {
            // Create random start point
            Point p1 = getRandomPoint();

            // Create random length ( 4 < len < 10 )
            int length = r.nextInt(10 - 4 + 1) + 4;

            // Rule out possible directions to build wall
            int direction = pickDirection(p1, length);
            //int direction = Direction.DOWN;

            int x = (int) p1.getX();
            int y = (int) p1.getY();

            // Add additional points based on direction
            this.addPoints(direction, x, y, length);

            okay = this.checkPoints();
        }

        // TODO: Debugging, remove
        this.points = WALL2;

    }

    private Point getRandomPoint() {
        r = new Random();
        int randomX = r.nextInt(Game.GRID_WIDTH);
        int randomY = r.nextInt(Game.GRID_HEIGHT);
        Point randomPoint = new Point(randomX, randomY);

        return randomPoint;
    }

    private int pickDirection(Point p, Integer length) {
        ArrayList<Integer> directions = new ArrayList<>();

        // If p1.x not too close to 0
        if (p.getX() >= (length - 1)) {
            directions.add(Direction.LEFT);
        }
        // If p1.x not too close to grid.width
        if (p.getX() + (length - 1) < Game.GRID_WIDTH) {
            directions.add(Direction.RIGHT);
        }
        // If p1.x not too close to 0
        if (p.getY() >= (length - 1)) {
            directions.add(Direction.UP);
        }
        // If p1.y not too close to grid.height
        if (p.getY() + (length - 1) < Game.GRID_HEIGHT) {
            directions.add(Direction.DOWN);
        }

        int max = directions.size() - 1;
        int randomNumber = r.nextInt(max + 1);
        int direction = directions.get(randomNumber);

        return direction;
    }

    public LinkedList<Point> getPoints() {
        return this.points;
    }

    public void addPoints(int direction, int x, int y, int length) {
        switch(direction) {
            case Direction.LEFT:
                for (int i = x; i >= x - length + 1; i--) {
                    points.add(new Point(i, y));
                }
                break;
            case Direction.RIGHT:
                for (int i = x; i <= x + length - 1; i++) {
                    points.add(new Point(i, y));
                }
                break;
            case Direction.UP:
                for (int i = y; i >= y - length + 1; i--) {
                    points.add(new Point(x, i));
                }
                break;
            case Direction.DOWN:
                for (int i = y; i <= y + length - 1; i++) {
                    points.add(new Point(x, i));
                }
                break;
            default:
                throw new Error("Wall.Wall() has no direction");

        }
    }

    private Boolean checkPoints() {
        Boolean okay = true;

        for (Point p : points) {
            if (handler.checkPoint(p) != ID.Empty) {
                okay = false;
            }
        }

        return okay;
    }

    private LinkedList<Point> generateWall(int startX, int startY, int endX, int endY) {

        LinkedList<Point> wall = new LinkedList<>();

        if (startX - endX == 0) {
            // Vertical wall
            for (int y = startY; y <= endY; y++) {
                wall.add(new Point(startX, y));
            }
            // Mirror horizontally
            wall = mirrorHorizontally(wall);

        } else if (startY - endY == 0) {
            // Horizontal wall
            for (int x = startX; x <= endX; x++) {
                wall.add(new Point(x, startY));
            }
            // Mirror vertically
            wall = mirrorVertically(wall);

        } else {
            // error, not a straight line
            throw new InvalidParameterException("Wall co-ordinates are not a straight line");
        }

        return wall;

    }

    private LinkedList<Point> mirrorHorizontally(LinkedList<Point> wall) {

        LinkedList<Point> mirrored = new LinkedList<>();

        // Iterate over points and mirror each
        for (Point p: wall) {

            mirrored.add(p);

            int x = (int) p.getX();
            int y = (int) p.getY();
            int x2 = (Game.GRID_WIDTH - x) - 1;
            Point tempPoint = new Point(x2,y);

            mirrored.add(tempPoint);

        }

        return mirrored;
    }

    private LinkedList<Point> mirrorVertically(LinkedList<Point> wall) {

        LinkedList<Point> mirrored = new LinkedList<>();

        // Iterate over points and mirror each
        for (Point p: wall) {

            mirrored.add(p);

            int x = (int) p.getX();
            int y = (int) p.getY();
            int y2 = (Game.GRID_HEIGHT - y) - 1;
            Point tempPoint = new Point(x,y2);

            mirrored.add(tempPoint);

        }

        return mirrored;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Colors.WALL);
        // Render wall
        for (Point p : points) {
            g.fillRect(p.x * Game.BOX_WIDTH, p.y * Game.BOX_HEIGHT + Game.GRID_YOFFSET, Game.BOX_WIDTH, Game.BOX_HEIGHT);
        }
    }
}
