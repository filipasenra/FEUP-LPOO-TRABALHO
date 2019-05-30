package com.pac_xon;

import javax.swing.*;
import java.awt.*;

public class Monster extends Element {

    private Image image;

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(image, position.getX()*50, position.getY()*100, null);
    }

    enum TYPE_WALL {Sides, Tops}

    private int speed_y = 1;
    private int speed_x = 1;

    public Monster(Position position, String symbol, String color) {
        super(position, symbol, color);

        ImageIcon ii = new ImageIcon("swing_images/green_ghost.png");
        image = ii.getImage();
        image = image.getScaledInstance(50, 100, Image.SCALE_SMOOTH);
    }

    public void changeMov(TYPE_WALL type_wall)
    {
        switch (type_wall)
        {
            case Sides:
                speed_x *= -1;
                break;
            case Tops:
                speed_y *= -1;
                break;
        }
    }

    @Override
    Position move() {

        return new Position(position.getX() + speed_x, position.getY() + speed_y);
    }


    public int getSpeed_y() {
        return speed_y;
    }

    public int getSpeed_x() {
        return speed_x;
    }


}
