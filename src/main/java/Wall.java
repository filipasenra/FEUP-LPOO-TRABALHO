import com.googlecode.lanterna.TerminalPosition;
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
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), this.symbol);
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
