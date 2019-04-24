import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    int width;
    int height;
    BackGround background;
    Player player;
    Wall wall;
    List<Monster> monsters = new ArrayList<>();

    boolean gameOver = false;
    boolean finishLevel = false;

    private void init(int width, int height) {
        this.width = width;
        this.height = height;
        createMonsters(2);
        this.background = new BackGround(width, height, " ","#3f3832");
    }

    public Arena(int width, int height, Player p, Wall w) {
        init(width,height);
        this.player = p;
        this.wall = w;
    }

    public Arena(int width, int height) {
        init(width,height);
        player = new Player(new Position(0,0), "C", "#FFFF33");
        wall = new Wall(width, height, " ", "#000080");
    }

    private void createMonsters(int no_monsters) {
        for (int i=0; i<no_monsters; i++)
            monsters.add(new Monster(generatePositions(), "X", "#FFCCD5"));
    }

    public Position generatePositions() {
        // create instance of Random class
        Random rand = new Random();

        int x = rand.nextInt(width-3) + 1;
        int y = rand.nextInt(height-3) + 1;

        return new Position(x, y);
    }

    public void draw(TextGraphics graphics) {
        background.draw(graphics);

        this.move();

        wall.draw(graphics);
        player.draw(graphics);

        for (Monster monster: monsters) {
            monster.draw(graphics);
        }
    }

    public void move()
    {
        playerMove(player.getPosition());

        for (Monster monster: monsters) {
            Position pos = monster.move();

            if(wall.getWall(pos.getX(), pos.getY()) == Wall.TYPE.Sea)
                monsterMove(monster);
            else if (checkCollision(pos))
                gameOver = true;
        }

        if(wall.percentage_fill() >= 80)
            finishLevel = true;


    }

    private boolean canPlayerMove(Position position)
    {
        if(position.getX() < 0 || position.getX() >= width)
            return false;

        if(position.getY() < 0 || position.getY() >= height)
            return false;

        return true;
    }

    private void playerMove(Position position) {
        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Wall)
            return;

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Construction) {
            gameOver = true;
            return;

        }


        wall.addPath(position.getX(), position.getY());

        if (canPlayerMove(player.move()))
            player.setPosition(player.move());




        if (wall.getWall(player.getPosition().getX(), player.getPosition().getY()) == Wall.TYPE.Wall)
            wall.fillWall(monsters);
    }

    //The monsters walk in diagonals and change direction every time he hits a wall
    private void monsterMove(Monster monster)
    {
        Position position = monster.move();

        monster.setPosition(position);

        //Check monsters' colisions with the walls
        if (wall.getWall(position.getX()+1, position.getY()) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Sides);

        if (wall.getWall(position.getX()-1, position.getY()) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Sides);

        if (wall.getWall(position.getX(), position.getY()+1) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Tops);

        if (wall.getWall(position.getX(), position.getY()-1) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Tops);
    }

    //Check if a monster touched a construction wall
    public boolean checkCollision (Position position) {

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Construction || player.getPosition().equals(position)) {
            return true;
        }

        return false;
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

        if(wall.getWall(player.position.getX(), player.position.getY()) == Wall.TYPE.Wall)
        {
            if(canPlayerMove(player.move()))
                player.setPosition(player.move());
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isFinishLevel() {
        return finishLevel;
    }
}
