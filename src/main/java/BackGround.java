import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BackGround extends Item{
    public int width;
    public int height;

    public BackGround(int width, int height, char symbol, String color) {
        super(symbol, color);
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(this.color));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), this.symbol);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}