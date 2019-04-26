import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Score extends Item {

    int width;
    double score;

    public Score(String symbol, String color, int width) {
        super(symbol, color);
        this.width = width;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        String score_to_be_shown = ((int) this.score + this.symbol);
        graphics.putString(new TerminalPosition(this.width - score_to_be_shown.length(), 0), score_to_be_shown);

    }

    public void setScore(double score) {
        this.score = score;
    }
}
