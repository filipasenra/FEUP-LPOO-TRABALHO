import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Menu {
    private int width;
    private int height;

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void gameOvermenu(TextGraphics graphics, int score)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        String to_be_shown = "Game Over";
        graphics.putString(new TerminalPosition(this.width/2 - to_be_shown.length()/2, height/2), to_be_shown);
        String score_to_be_shown = "Score: " + score;
        graphics.putString(new TerminalPosition(this.width/2 - score_to_be_shown.length()/2, height/2 +1), score_to_be_shown);

    }

    public void nextLevelmenu(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        String to_be_shown = "Next Level!";
        graphics.putString(new TerminalPosition(this.width/2 - to_be_shown.length()/2, height/2), to_be_shown);

    }
}
