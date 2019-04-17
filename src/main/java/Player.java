public class Player extends Element{

    enum DIRECTION {
        NORTH,
        SOUTH,
        WEST,
        EAST
    }

    private DIRECTION direction = DIRECTION.EAST;
    int speed = 1;

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
    public Position move() {

        Position position = new Position(0, 0);

        switch (direction) {
            case NORTH:
                position = new Position(this.position.getX(), this.position.getY() - speed);
                break;
            case SOUTH:
                position = new Position(this.position.getX(), this.position.getY() + speed);
                break;
            case WEST:
                position = new Position(this.position.getX() - speed, this.position.getY());
                break;
            case EAST:
                position = new Position(this.position.getX() + speed, this.position.getY());
                break;
        }

        return position;

    }
}
