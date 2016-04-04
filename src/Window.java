import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    // This class creates the window for us

    private static final long serialVersionUID = 3214894526794312123L;

    public Window(String title, Game game) {
        JFrame frame = new JFrame(title);

        int width = Game.TOTAL_WIDTH;
        int height = Game.TOTAL_HEIGHT + 22 + Game.GRID_YOFFSET;

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        // So close button actually works
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Makes life easier for now
        frame.setResizable(false);

        // main.java.Window starts in middle of screen
        frame.setLocationRelativeTo(null);

        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}