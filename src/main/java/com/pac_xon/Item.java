package com.pac_xon;

import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;
import java.awt.*;

public abstract class Item extends JComponent {

    //symbol to be represent Object
    protected String symbol;

    //color
    protected String color;

    public Item(String symbol, String color) {
        this.symbol = symbol;
        this.color = color;
    }

    @Override
    abstract public Dimension getPreferredSize();
}
