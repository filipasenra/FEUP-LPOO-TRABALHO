import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Item {
    //Position of object
    protected Position position;

    public Wall(String symbol, String color, Position position) {
        super(symbol, color);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(this.color));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(position.getX()+1, position.getY()+1), this.symbol.charAt(0));

    }
}
