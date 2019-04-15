import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Arena {
    int width;
    int height;
    BackGround background;
    Player player = new Player(new Position(0,0), "C", "#FFFF33");
    HashSet<Wall> walls;
    boolean pressed_key = false;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        this.background = new BackGround(70, 20, " ","#3f3832");

        this.walls = createWalls();
    }

    public void draw(TextGraphics graphics) {
        background.draw(graphics);

        this.move();
        pressed_key = false;

        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        player.draw(graphics);
    }

    public void move()
    {
        for(Wall wall: walls)
        {
            if(this.player.getPosition().equals(wall.getPosition())) {
                if(pressed_key)
                    break;
                else
                    return;
            }

        }

        walls.add(new Wall(this.player.getPosition(), " ", "#000080"));
        player.move(background.getWidth(), background.getHeight());
    }

    private HashSet<Wall> createWalls() {
        HashSet<Wall> walls = new HashSet();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(new Position(c, 0), " ", "#000080"));
            walls.add(new Wall(new Position(c, height-1), " ", "#000080"));
        }

        for (int r = 0; r < height - 1; r++) {
            walls.add(new Wall( new Position( 0, r), " ", "#000080"));
            walls.add(new Wall( new Position( width-1, r), " ", "#000080"));
        }

        return walls;
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
