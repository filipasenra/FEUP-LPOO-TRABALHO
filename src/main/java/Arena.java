import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.util.*;

public class Arena {
    int width;
    int height;
    BackGround background;
    Player player = new Player(new Position(0,0), "C", "#FFFF33");
    boolean pressed_key = false;
    Wall wall;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;

        wall = new Wall(70, 20, " ", "#000080");

        this.background = new BackGround(70, 20, " ","#3f3832");
    }

    public void draw(TextGraphics graphics) {
        background.draw(graphics);

        this.move();
        pressed_key = false;

        wall.draw(graphics);
        player.draw(graphics);
    }

    public void move()
    {
        if(wall.getWall(player.getPosition().getX(), player.getPosition().getY()) && !pressed_key)
            return;

        wall.addWall(player.getPosition().getX(), player.getPosition().getY());
        player.move(background.getWidth(), background.getHeight());
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
