import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class MovingElement extends Element {

    public MovingElement(Position position, String symbol, String color) {
        super(position, symbol, color);
    }

    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), this.symbol);

    }

    abstract boolean move(int x, int y);
}
