package com.pac_xon;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Item {

    //symbol to be represent Object
    protected String symbol;

    //color
    protected String color;

    public Item(String symbol, String color) {
        this.symbol = symbol;
        this.color = color;
    }

}
