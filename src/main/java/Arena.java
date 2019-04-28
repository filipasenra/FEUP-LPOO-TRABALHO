import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
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
    Score score;
    Lives lives;

    boolean finishLevel = false;

    private void init(int width, int height, int no_monsters) {
        this.width = width;
        this.height = height - 1;
        createMonsters(no_monsters);
        this.background = new BackGround(width, height, " ","#3f3832");
        this.score = new Score("%/80%", "#000000", width);
    }

    public Arena(int width, int height, Player p, Wall w) {
        init(width,height, 2);
        this.player = p;
        this.wall = w;
        this.lives = new Lives("Lives: ", "#000000", 4);
    }

    public Arena(int width, int height, int lives, int no_monsters) {
        init(width,height, no_monsters);
        player = new Player(new Position(0,0), "C", "#FFFF33");
        wall = new Wall(this.width, this.height, " ", "#000080");
        this.lives = new Lives("Lives: ", "#000000", lives);
    }

    private void createMonsters(int no_monsters) {
        for (int i=0; i<no_monsters; i++)
            monsters.add(new Monster(generatePositions(), "X", "#FFCCD5"));
    }

    public Position generatePositions() {
        // create instance of Random class
        Random rand = new Random();

        int x = rand.nextInt(width-3) + 2;
        int y = rand.nextInt(height-3) + 2;

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

        this.score.draw(graphics);

        this.lives.draw(graphics);
    }

    public void move()
    {
        playerMove(player.getPosition());

        for (Monster monster: monsters) {
            if (checkCollision(monster.move()))
                resetGame();
                monsterMove(monster);
        }

        this.score.setScore(wall.percentage_fill());

        if(wall.percentage_fill() >= 80)
            finishLevel = true;
    }

    private void resetGame()
    {
        if(lives.getLives() == 0)
            return;

        lives.setLives(lives.getLives()-1);

        player.setPosition(new Position(0, 0));

        wall.eraseConstructionSea(Wall.TYPE.Construction);
    }

    private boolean canPlayerMove(Position position)
    {
        if(position.getX() < 0 || position.getX() >= width)
            return false;

        if(position.getY() < 0 || position.getY() >= height)
            return false;

        return true;
    }

    private boolean playerMove(Position position) {

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Wall)
            return true;

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Construction) {
            resetGame();
            return false;
        }

        wall.addPath(position.getX(), position.getY());

        if (canPlayerMove(player.move()))
            player.setPosition(player.move());
        
        if (wall.getWall(player.getPosition().getX(), player.getPosition().getY()) == Wall.TYPE.Wall)
            wall.fillWall(monsters);

        return true;
    }

    //The monsters walk in diagonals and change direction every time he hits a wall
    public void monsterMove(Monster monster) {
        Position position = monster.move();

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Wall) {
            monster.changeMov(Monster.TYPE_WALL.Sides);
            monster.changeMov(Monster.TYPE_WALL.Tops);

            monster.setPosition(monster.move());
            return;
        }

        monster.setPosition(monster.move());

        //Check monsters' colisions with the walls
        if (wall.getWall(position.getX() + 1, position.getY()) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Sides);

        if (wall.getWall(position.getX() - 1, position.getY()) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Sides);

        if (wall.getWall(position.getX(), position.getY() + 1) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Tops);

        if (wall.getWall(position.getX(), position.getY() - 1) == Wall.TYPE.Wall)
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
        return lives.getLives() == 0;
    }

    public boolean isFinishLevel() {
        return finishLevel;
    }
}
