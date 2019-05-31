package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.pac_xon.Game;
import com.pac_xon.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameFrame extends JFrame {

    private final int xResolution = 1400;
    private final int yResolution = 380;

    private GameData gameData;
    public ScoreData scoreData;

    public GameFrame(Model model) throws IOException {
        this.setTitle("Pac Xon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)
        );

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Plus 76 for the frame on top i think? + 20 to Score data
        this.setBounds(0, 0, xResolution, yResolution + 76 + 20);

        this.gameData = new GameData(model, xResolution, yResolution);

        this.scoreData = new ScoreData(model, xResolution, yResolution);


        this.getContentPane().add(scoreData);
        this.getContentPane().add(gameData);

        this.setVisible(true);
    }

}
