import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element extends Item {

    //Position of object
    protected Position position;

    public Element(Position position, String symbol, String color) {

        super(symbol, color);
        this.position = position;
    }

    @Override
    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), this.symbol);

    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public Position getPosition()
    {
        return this.position;
    }

    public abstract boolean move(int x, int y);
}
