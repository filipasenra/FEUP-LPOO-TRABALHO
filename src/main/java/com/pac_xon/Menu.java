package com.pac_xon;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.*;
import java.awt.*;

public class Menu extends JComponent{
    private int width;
    private int height;

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /*public void gameOvermenu(JFrame frame, int score)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        String to_be_shown = "Game Over";
        graphics.putString(new TerminalPosition(this.width/2 - to_be_shown.length()/2, height/2), to_be_shown);
        String score_to_be_shown = "Score: " + score;
        graphics.putString(new TerminalPosition(this.width/2 - score_to_be_shown.length()/2, height/2 +1), score_to_be_shown);
    }

    public void nextLevelmenu(JFrame frame)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#ffffff"));
        graphics.enableModifiers(SGR.BOLD);
        String to_be_shown = "Next Level!";
        graphics.putString(new TerminalPosition(this.width/2 - to_be_shown.length()/2, height/2), to_be_shown);
    }*/

    public void startGamemenu(JFrame frame)
    {

        /*graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        String name_to_be_shown = "PAC XON";
        graphics.putString(new TerminalPosition(this.width/2 - name_to_be_shown.length()/2, 5), name_to_be_shown);
        String to_be_shown = "New game";
        graphics.putString(new TerminalPosition(this.width/2 - to_be_shown.length()/2, height/2), to_be_shown);
        String _to_be_shown = "Press enter to start";
        graphics.putString(new TerminalPosition(this.width/2 - _to_be_shown.length()/2, height/2 +1), _to_be_shown);

         */
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        String name_to_be_shown = "PAC XON";
        g.drawString(name_to_be_shown, 300,300);
    }
}
