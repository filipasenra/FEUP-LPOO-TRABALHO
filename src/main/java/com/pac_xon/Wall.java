package com.pac_xon;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Wall extends Item {

    enum TYPE {Wall, Sea, Construction, Side1}

    public TYPE walls_array[][];
    private int width;
    private int height;

    public Wall(int width, int height, String symbol, String color) {
        super(symbol, color);

        this.width = width;
        this.height = height;

        walls_array = new TYPE[width][height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (i == 0 || j == this.height - 1 || j == 0 || i == this.width - 1) {
                    walls_array[i][j] = TYPE.Wall;
                    continue;
                }

                walls_array[i][j] = TYPE.Sea;
            }
        }
    }

    public TYPE[][] getWalls_array() {
        return walls_array;
    }


    public void setWalls_array(TYPE[][] walls_array) {
        this.walls_array = walls_array;
    }

    @Override
    public void draw(TextGraphics graphics) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (walls_array[i][j] == TYPE.Wall || walls_array[i][j] == TYPE.Construction) {

                    graphics.setBackgroundColor(TextColor.Factory.fromString("#000080"));
                    graphics.putString(new TerminalPosition(i, j + 1), " ");
                }
            }
        }

    }

    public void addWall(int x, int y) {

        walls_array[x][y] = TYPE.Wall;
    }

    public void addPath(int x, int y) {

        walls_array[x][y] = TYPE.Construction;
    }

    public TYPE getWall(int x, int y) {
        return walls_array[x][y];
    }


    public void fillWall(List<Monster> monsters) {

        //Runs line by line, checking for the construction line
        //When it founds the construction line, checks the upper, down and then left and right side
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {

                if (walls_array[i][j] == TYPE.Construction) {

                    if (walls_array[i][j - 1] == TYPE.Sea) {
                        {
                            apply(i, j - 1, TYPE.Side1);
                            finishConstruction(monsters, TYPE.Side1);
                        }
                    }

                    if (walls_array[i][j + 1] == TYPE.Sea) {
                        {
                            apply(i, j + 1, TYPE.Side1);
                            finishConstruction(monsters, TYPE.Side1);
                        }
                    }

                    if (walls_array[i - 1][j] == TYPE.Sea) {
                        {
                            apply(i - 1, j, TYPE.Side1);
                            finishConstruction(monsters, TYPE.Side1);
                        }
                    }

                    if (walls_array[i + 1][j] == TYPE.Sea) {
                        {
                            apply(i + 1, j, TYPE.Side1);
                            finishConstruction(monsters, TYPE.Side1);
                        }
                    }
                }
            }
        }

        eraseConstructionWall(TYPE.Construction);
    }

    private void finishConstruction(List<Monster> monsters, TYPE side)
    {
        //After we check for all the sides, we finish the construction:
        //1. We transform the construction into wall
        //2. The smallest polygon into wall
        //3. And the other side of the polygon into see

        if(checkToFill(monsters, side))
        {
            eraseConstructionWall(side);
        }
        else
            eraseConstructionSea(side);
    }

    private boolean checkToFill( List<Monster> monsters, TYPE side)
    {
        for(Monster monster : monsters)
        {
            if (walls_array[monster.getPosition().getX()][monster.getPosition().getY()] == side)
                return false;
        }

        return true;
    }

    private void eraseConstructionWall(TYPE side) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (walls_array[i][j] == side)
                {
                    walls_array[i][j] = TYPE.Wall;
                }
            }
        }
    }

    public void eraseConstructionSea(TYPE side) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (walls_array[i][j] == side)
                {
                    walls_array[i][j] = TYPE.Sea;
                }
            }
        }
    }

    private int apply(int x, int y, TYPE side) {
        int n = 0;

        if (walls_array[x][y] == TYPE.Sea) {
            n++;
            walls_array[x][y] = side;
            n += apply(x + 1, y, side);
            n += apply(x - 1, y, side);
            n += apply(x, y + 1, side);
            n += apply(x, y - 1, side);
        }

        return n;
    }

    public double percentage_fill()
    {
        int total_area = (width-2)*(height-2);

        int n_wall=0;

        for (int i = 1; i < width-1; i++) {
            for (int j = 1; j < height-1; j++) {
                if (walls_array[i][j] == TYPE.Wall) {
                    n_wall++;
                }
            }
        }

        return (n_wall*100.0)/total_area;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
