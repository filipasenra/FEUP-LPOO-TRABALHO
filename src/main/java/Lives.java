import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Lives extends Item {

    private int lives;

    public Lives(String symbol, String color, int lives) {
        super(symbol, color);
        this.lives = lives;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(0, 0), (this.symbol + this.lives));
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives()
    {
        return this.lives;
    }
}
