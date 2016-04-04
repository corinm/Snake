import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Handler {

    private Map<Point, GameObject> allPoints;
    LinkedList<GameObject> objects;
    Snake snake;
    Fruit fruit;
    Wall wall;
    private ScoreKeeper sk;

    public Handler() {

        allPoints = new HashMap<>();
        objects = new LinkedList<>();
        sk = new ScoreKeeper(this);

    }

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
    }

    public void createSnake() {
        Snake newSnake = new Snake(this, sk);
        objects.add(newSnake);
        sk.setSnake(newSnake);
        this.snake = newSnake;
    }

    public void clearPoints() {
        allPoints.clear();
    }

    public void addPoint(Point p, GameObject g) {
        allPoints.put(p, g);
    }

    public void removePoint(Point p) {
        allPoints.remove(p);
    }

    public ID checkPoint(Point p) {

        ID id = ID.Empty;

        if(allPoints.containsKey(p)) {
            GameObject tempObject = allPoints.get(p);
            id = tempObject.getId();

            if(id == null) {
                throw new NullPointerException("Handler.checkPoint() is about to return null");
            }

        }
        return id;
    }

    public void addFruit() {
        fruit = new Fruit(this);
        addPoint(fruit.getPoint(), fruit);
        objects.add(fruit);
    }

    public void removeFruit(Point p) {
        GameObject tempFruit = allPoints.get(p);
        removePoint(p);
        objects.remove(tempFruit);
    }

    public void addFruitRotten() {
        fruit = new FruitRotten(this);
        addPoint(fruit.getPoint(), fruit);
        objects.add(fruit);
    }

    public void addWall() {
        wall = new Wall(this, snake);
        LinkedList tempPoints = wall.getPoints();

        for (Iterator i = tempPoints.iterator(); i.hasNext(); ) {
            addPoint((Point) i.next(), wall);
            objects.add(wall);
        }

    }

    public void createObjects() {
        createSnake();
        addFruit();
        addWall();
        sk.reset();
    }

    public void reset() {
        // Reset game, i.e. reset snake, fruit and score
        allPoints.clear();
        objects.clear();
        createObjects();
    }

    public ScoreKeeper getScoreKeeper() {
        return sk;
    }
}
