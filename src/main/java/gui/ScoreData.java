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

    JLabel score;
    JLabel lives;

    private int charactersWidth;
    private int charactersHeight;

    int a;

    public ScoreData(Model model, int width, int height) throws IOException {
        this.model = model;

        setFocusable(true);
        setDoubleBuffered(true);

        this.charactersWidth = width/model.getArena().getWidth();
        this.charactersHeight = height/(model.getArena().getHeight());

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        score = new JLabel("Score: " + 0);
        score.setFont(new Font("Courier New", Font.BOLD, 20));
        this.add(score);


        lives = new JLabel("Lives: " + 0);
        lives.setFont(new Font("Courier New", Font.BOLD, 20));
        this.add(lives);
    }

    public void updateScore(){

        this.score.setText("Score: " + model.getArena().getScore().getScore());

        this.lives.setText("Lives: " + model.getArena().getLives().getLives());
    }


}

