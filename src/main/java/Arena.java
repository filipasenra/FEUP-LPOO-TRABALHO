import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    int width;
    int height;
    BackGround background = new BackGround(70, 20, " ","#3f3832");
    Player player = new Player(new Position(0,0), "C", "#ffffff");
    List<Wall> walls;


    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
    }

    public void draw(TextGraphics graphics) {
        background.draw(graphics);
        player.move(background.getWidth(), background.getHeight());

        for (Wall wall : walls) {
            wall.draw(graphics);
        }

        player.draw(graphics);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(" ", "#000080", new Position(c, 0)));
            walls.add(new Wall(" ", "#000080", new Position(c, height-1)));
        }

        for (int r = 0; r < height - 1; r++) {
            walls.add(new Wall(" ", "#000080", new Position( 0, r)));
            walls.add(new Wall(" ", "#000080", new Position( width-1, r)));
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
                ;
                break;
            case ArrowRight:
                this.player.setDirection(Player.DIRECTION.EAST);
                break;
            case ArrowUp:
                this.player.setDirection(Player.DIRECTION.NORTH);
                break;
        }
    }

}
