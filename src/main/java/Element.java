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

    public abstract void draw(TextGraphics graphics);

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public Position getPosition()
    {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Wall p = (Wall) o;

        return position.equals(p.getPosition());

    }
}
