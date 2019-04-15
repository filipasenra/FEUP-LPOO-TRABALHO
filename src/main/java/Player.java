import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Player extends Element{

    enum DIRECTION {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    private DIRECTION direction = DIRECTION.EAST;

    public Player(Position position, String symbol, String color) {
        super(position, symbol, color);
    }

    public void setDirection(DIRECTION direction)
    {
        this.direction = direction;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    @Override
    public boolean move(int x, int y) {

        Position position;

        switch (direction) {
            case NORTH:
                position = new Position(this.position.getX(), this.position.getY() - 1);
                break;
            case SOUTH:
                position = new Position(this.position.getX(), this.position.getY() + 1);
                break;
            case WEST:
                position = new Position(this.position.getX() - 1, this.position.getY());
                break;
            case EAST:
                position = new Position(this.position.getX() + 1, this.position.getY());
                break;

            default:
                return false;
        }

        if(position.getX() < 0 || position.getX() >= x)
            return false;

        if(position.getY() < 0 || position.getY() >= y)
            return false;

        this.setPosition(position);

        return true;

    }
}
