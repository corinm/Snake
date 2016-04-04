import java.awt.*;

public class FruitRotten extends Fruit {

    public FruitRotten(Handler handler) {
        super(handler);

        this.id = ID.FruitRotten;

        generatePoint();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Colors.FRUIT_ROTTEN);
        // Render fruit
        int x = point.x * Game.BOX_WIDTH;
        int y = point.y * Game.BOX_HEIGHT;
        g.fillOval(x, y + Game.GRID_YOFFSET, Game.BOX_WIDTH, Game.BOX_HEIGHT);
    }

}
