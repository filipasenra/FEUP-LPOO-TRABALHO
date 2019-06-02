package gui.ViewSwing;

import com.pac_xon.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, getWidth(), getHeight());


        graphics.setFont(new Font("Courier New", Font.BOLD, 20));
        graphics.setColor(Color.yellow);

        graphics.drawString((int) model.getArena().getPercentage().getPercentage() + "%/80%",width - 115, 20);
        graphics.drawString("Score: " + model.getArena().getScore().getScore(), width/2 - 75, 20);
        graphics.drawString("Lives: " + model.getArena().getLives(), 0, 20);
    }
}

