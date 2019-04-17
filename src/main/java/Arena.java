import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Arena {
    int width;
    int height;
    BackGround background;
    Player player = new Player(new Position(0,0), "C", "#FFFF33");
    boolean pressed_key = false;
    boolean outside_wall = false;
    Wall wall;
    Monster monster;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        monster = new Monster(new Position(7, 7), "X", "#FFCCD5");

        wall = new Wall(70, 20, " ", "#000080");

        this.background = new BackGround(70, 20, " ","#3f3832");
    }

    public void draw(TextGraphics graphics) {
        background.draw(graphics);

        this.move();
        pressed_key = false;

        wall.draw(graphics);
        player.draw(graphics);
        monster.draw(graphics);
    }

    public void move()
    {
        monsterMove();

        //If the player is inside the wall
        if(wall.getWall(player.getPosition().getX(), player.getPosition().getY()) == Wall.TYPE.Wall)
        {
            //If it has returned from the sea, let's finish the construction
            if(outside_wall)
                wall.fillWall();

            //The player is in the wall
            outside_wall = false;

            //If it has not pressed any key, it doesn't move
            if(!pressed_key)
                return;

            wall.addWall(player.getPosition().getX(), player.getPosition().getY());
            playerMove();

            return;
        }

        //The player is in the sea
        outside_wall = true;

        wall.addPath(player.getPosition().getX(), player.getPosition().getY());
        playerMove();

    }

    private void playerMove()
    {
        Position position = player.move();

        if(position.getX() < 0 || position.getX() >= width)
            return;

        if(position.getY() < 0 || position.getY() >= height)
            return;

        this.player.setPosition(position);
    }

    private void monsterMove()
    {
        Position position = monster.move();

        if (!(wall.getWall(position.getX()+1, position.getY()) == Wall.TYPE.Sea)) {
            monster.changeMov(Monster.TYPE_WALL.Sides);
        }

        else if (!(wall.getWall(position.getX()-1, position.getY()) == Wall.TYPE.Sea)) {
            monster.changeMov(Monster.TYPE_WALL.Sides);
        }

        else if (!(wall.getWall(position.getX(), position.getY()+1) == Wall.TYPE.Sea)) {
            monster.changeMov(Monster.TYPE_WALL.Tops);
        }

        else if (!(wall.getWall(position.getX(), position.getY()-1) == Wall.TYPE.Sea)) {
            monster.changeMov(Monster.TYPE_WALL.Tops);
        }

        this.monster.setPosition(position);
    }

    public void processKey(KeyStroke key) {

        switch (key.getKeyType()) {
            case ArrowDown:
                this.player.setDirection(Player.DIRECTION.SOUTH);
                break;
            case ArrowLeft:
                this.player.setDirection(Player.DIRECTION.WEST);
                break;
            case ArrowRight:
                this.player.setDirection(Player.DIRECTION.EAST);
                break;
            case ArrowUp:
                this.player.setDirection(Player.DIRECTION.NORTH);
                break;
        }

        pressed_key = true;
    }


}
