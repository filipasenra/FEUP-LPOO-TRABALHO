import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Item {

    boolean walls_array[][];
    int width;
    int height;

    public Wall(int width, int height, String symbol, String color) {
        super(symbol, color);

        this.width = width;
        this.height = height;

        walls_array = new boolean[width][height];

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                if(i == 0 || j == height-1 || j == 0 || i == width - 1)
                {
                    walls_array[i][j] = true;
                    continue;
                }

                walls_array[i][j] = false;
            }
        }

    }

    @Override
    public void draw(TextGraphics graphics) {

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                if(walls_array[i][j]) {

                    graphics.setBackgroundColor(TextColor.Factory.fromString("#000080"));
                    graphics.putString(new TerminalPosition(i, j), " ");
                }
            }
        }

    }

    public void addWall(int x, int y){

        walls_array[x][y] = true;
    }

    public boolean getWall(int x, int y)
    {
        return walls_array[x][y];
    }

    public void fillWall()
    {

    }
}
