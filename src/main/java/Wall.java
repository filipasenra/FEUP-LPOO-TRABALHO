import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {

    public Wall(Position position, String symbol, String color) {
        super(position, symbol, color);
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), this.symbol);
    }
}
