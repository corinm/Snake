import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    // Array, to help prevent multiple keypresses at once
    private boolean[] keyDown = new boolean[4];

    Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (handler.snake != null) {

            int direction = handler.snake.getDirection();

            // If at start (not moving) and player presses down, reverse the snake
            if (direction == Direction.NO_DIRECTION && key == KeyEvent.VK_DOWN) {
                handler.snake.reverseSnake();
            }

            switch (key) {
                case KeyEvent.VK_UP:
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                    break;
            }

            handler.snake.setDirection(direction);

        }
    }

}