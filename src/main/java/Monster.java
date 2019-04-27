public class Monster extends Element {

    enum TYPE_WALL {Sides, Tops}

    private int speed_y = 1;
    private int speed_x = 1;

    public Monster(Position position, String symbol, String color) {
        super(position, symbol, color);
    }

    public void changeMov(TYPE_WALL type_wall)
    {
        switch (type_wall)
        {
            case Sides:
                speed_x *= -1;
                break;
            case Tops:
                speed_y *= -1;
                break;
        }
    }

    @Override
    Position move() {

        return new Position(position.getX() + speed_x, position.getY() + speed_y);
    }


    public int getSpeed_y() {
        return speed_y;
    }

    public int getSpeed_x() {
        return speed_x;
    }


}
