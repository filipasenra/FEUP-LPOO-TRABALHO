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

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public Position getPosition()
    {
        return this.position;
    }

    public void draw(TextGraphics graphics) {

        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), this.symbol);

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Element p = (Element) o;

        return position.equals(p.getPosition());

    }

    abstract boolean move(int x, int y);
}
