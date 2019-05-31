package com.pac_xon;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Percentage extends Item {

    private int width;
    private double percentage;

    public Percentage(String symbol, String color, int width) {
        super(symbol, color);
        this.width = width;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.enableModifiers(SGR.BOLD);
        String percentage_to_be_shown = ((int) this.percentage + this.symbol);
        graphics.putString(new TerminalPosition(this.width - percentage_to_be_shown.length(), 0), percentage_to_be_shown);
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }
}
