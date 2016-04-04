import java.awt.*;
import java.awt.image.BufferStrategy;

// TODO: Do something with steps/score/level
// TODO: Add customised walls based on level(?)
// TODO: Refactor to allow multiple fruit
// TODO: Add apple shapes for fruit
// TODO: Add superfruit (+2)

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1248932963571468247L;

    public static final int BOX_WIDTH = 15;
    public static final int BOX_HEIGHT = 15;
    public static final int GRID_WIDTH = 35;
    public static final int GRID_HEIGHT = 35;
    public static final int TOTAL_WIDTH = BOX_WIDTH * GRID_WIDTH;
    public static final int TOTAL_HEIGHT = BOX_HEIGHT * GRID_HEIGHT;
    public static final int GRID_YOFFSET = 20;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Menu menu;

    public enum STATE {
        Menu,
        Game,
        End,
    }

    public STATE gameState = STATE.Menu;


    public Game() {

        handler = new Handler();
        hud = new HUD(handler.getScoreKeeper());
        menu = new Menu(this, handler);

        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        // Goes at end of Game constructor
        new Window("Snake", this);
    }

    public static void main(String[] args) {

        new Game();

    }

    // Starts up thread
    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    // Game loop, to update itself - DONE?
    public void run() {
        // So you don't need to click on screen
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 12.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (running) {
            long now= System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }

        }
        stop();
    }

    public void tick() {

        if(gameState == Game.STATE.Game) {
            handler.tick();
        } else {
            handler.tick();
            menu.tick();
        }


    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            // Number of buffers it creates. 3 best, don't go over.
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        drawBackground(g);
        handler.render(g);

        if(gameState == STATE.Menu) {
            menu.render(g);
        } else {
            drawGrid(g);
            hud.render(g);
        }

        g.dispose();
        bs.show();

    }

    private void drawBackground(Graphics g) {

        g.setColor(Colors.BACKGROUND);
        g.fillRect(0, 0, TOTAL_WIDTH, TOTAL_HEIGHT + GRID_YOFFSET);

    }

    private void drawGrid(Graphics g) {

        g.setColor(Colors.GRID);
        // Draw outside rectangle
        g.drawRect(0, 0 + GRID_YOFFSET, BOX_WIDTH * GRID_WIDTH, BOX_HEIGHT * GRID_HEIGHT);

        // Draw vertical lines on x axis
        for (int x = BOX_WIDTH; x < (GRID_WIDTH * BOX_WIDTH); x += BOX_WIDTH) {
            // Start at end of first box, until reach end
            g.drawLine(x, 0 + GRID_YOFFSET, x, (BOX_HEIGHT * GRID_HEIGHT) + GRID_YOFFSET);
        }

        // Draw horizontal lines on y axis
        for (int y = BOX_HEIGHT + GRID_YOFFSET; y < (GRID_HEIGHT * BOX_HEIGHT) + GRID_YOFFSET; y += BOX_HEIGHT) {
            // Start at end of first box, until reach end
            g.drawLine(0, y, BOX_WIDTH * GRID_WIDTH, y);
        }
    }


}
