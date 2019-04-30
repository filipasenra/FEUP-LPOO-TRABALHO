import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Score extends Item {

    private int width;
    private int score;

    public Score(String symbol, String color, int width) {
        super(symbol, color);
        this.width = width;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.width/2 - 5, 0), this.symbol + this.score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
