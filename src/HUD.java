import java.awt.*;

public class HUD {

    private ScoreKeeper sk;

    HUD(ScoreKeeper sk) {
        this.sk = sk;
    }

    public void render(Graphics g) {

        g.setFont(Fonts.SMALL_HEAVY);
        g.setColor(Colors.HUD);

        g.drawString("Score: " + sk.getScore(), Game.TOTAL_WIDTH / 20, 15);
        g.drawString("Level: " + sk.getLevel(), Game.TOTAL_WIDTH - (Game.TOTAL_WIDTH / 7), 15);

    }

}