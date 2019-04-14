import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Arena {
    BackGround background = new BackGround(80, 24, ' ',"#336699");
    Player player = new Player(new Position(0,0), 'X', "#ffffff");

    public void draw(TextGraphics graphics)
    {
        background.draw(graphics);
        player.move(background.getWidth(), background.getHeight());
        player.draw(graphics);
    }

    public void processKey(KeyStroke key) {

        switch (key.getKeyType())
        {
            case ArrowDown:
                this.player.setDirection(Player.DIRECTION.SOUTH);
                break;
            case ArrowLeft:
                this.player.setDirection(Player.DIRECTION.WEAST);
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
