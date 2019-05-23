package com.pac_xon;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class BackGround extends Item{
    private int width;
    private int height;

    public BackGround(int width, int height, String symbol, String color) {
        super(symbol, color);
        this.width = width;
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString(this.color));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), this.symbol.charAt(0));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}