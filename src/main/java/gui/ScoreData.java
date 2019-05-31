package gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.pac_xon.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScoreData extends JPanel {

    private Model model;

    private int width;

    int a;

    public ScoreData(Model model, int width, int height) throws IOException {
        this.model = model;

        setFocusable(true);
        setDoubleBuffered(true);

        this.width = width;
    }


    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setFont(new Font("Courier New", Font.BOLD, 20));
        graphics.drawString("Score: " + model.getArena().getScore().getScore(), 0, 20);
        graphics.drawString("Lives: " + model.getArena().getLives().getLives(), width-200, 20);
    }
}

