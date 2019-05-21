package com.pac_xon;

import java.awt.*;

public class BackGround extends Item{
    private int width;
    private int height;

    public BackGround(int width, int height, String symbol, String color) {
        super(symbol, color);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, width, height);
    }
}