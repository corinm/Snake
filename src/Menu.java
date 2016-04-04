import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        if(game.gameState == Game.STATE.Menu) {
            // MAIN MENU BUTTONS

            // Play button
            if (mouseOver(mx, my, 235, 145, 45, 15)) {
                game.gameState = Game.STATE.Game;
                // Create bg, grid, snake and apple?
                handler.createObjects();
            }


        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < (x + width)) {
            if(my > y && my < (y + height)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {

        if (game.gameState == Game.STATE.Menu) {

            g.setColor(Colors.MENU_BACKGROUND);
            g.fillRect(0, 0, Game.TOTAL_WIDTH, Game.TOTAL_HEIGHT);

            g.setColor(Colors.MENU_TEXT);

            g.setFont(Fonts.BIG);
            g.drawString("Snake", 215, 70);

            g.setFont(Fonts.MEDIUM);

            g.drawString("Start", 235, 160);
            //g.drawRect(235, 145, 45, 15);

            g.drawString("Options", 220, 200);
            g.drawString("Exit", 235, 260);

        }

    }

}
