import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    int width;
    int height;
    BackGround background;
    Player player = new Player(new Position(0,0), "C", "#FFFF33");
    boolean pressed_key = false;
    boolean outside_wall = false;
    Wall wall;
    List<Monster> monsters = new ArrayList<>();

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        createMonsters(2);

        wall = new Wall(70, 20, " ", "#000080");
        this.background = new BackGround(70, 20, " ","#3f3832");
    }

    private void createMonsters(int no_monsters) {
        for (int i=0; i<no_monsters; i++)
            monsters.add(new Monster(generatePositions(), "X", "#FFCCD5"));
    }

    private Position generatePositions() {
        // create instance of Random class
        Random rand = new Random();

        int x = rand.nextInt(70);
        int y = rand.nextInt(20);

        if (x == 0 || y ==0)
            return generatePositions();
        else
            return new Position(x, y);
    }

    public void draw(TextGraphics graphics) {
        background.draw(graphics);

        this.move();
        pressed_key = false;

        wall.draw(graphics);
        player.draw(graphics);

        for (Monster monster: monsters) {
            monster.draw(graphics);
        }

    }

    public void move()
    {
        for (Monster monster: monsters) {
            Position pos = monsterMove(monster);
            if (pos == null)
                return;
            else
                monster.setPosition(pos);
        }


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

    //The monsters walk in diagonals and change direction every time he hits a wall
    private Position monsterMove(Monster monster)
    {
        Position position = monster.move();

        //If the monster touched a wall in construction stop the game
        if (!checkMove(position))
            return null;

        //Check monsters' colisions with the walls
        if (wall.getWall(position.getX()+1, position.getY()) == Wall.TYPE.Wall) {
            monster.changeMov(Monster.TYPE_WALL.Sides);
        }

        if (wall.getWall(position.getX()-1, position.getY()) == Wall.TYPE.Wall) {
            monster.changeMov(Monster.TYPE_WALL.Sides);
        }

        if (wall.getWall(position.getX(), position.getY()+1) == Wall.TYPE.Wall) {
            monster.changeMov(Monster.TYPE_WALL.Tops);
        }

        if (wall.getWall(position.getX(), position.getY()-1) == Wall.TYPE.Wall) {
            monster.changeMov(Monster.TYPE_WALL.Tops);
        }

        return position;
    }

    //Check if a monster touched a construction wall
    private boolean checkMove (Position position) {
        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Construction) {
            return false;
        }
        return true;
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
