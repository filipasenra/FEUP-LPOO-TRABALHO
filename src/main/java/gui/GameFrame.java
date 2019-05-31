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

    public GameFrame(Model model) throws IOException {
        this.setTitle("Pac Xon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Plus 76 for the frame on top i think?
        this.setBounds(0, 0, xResolution, yResolution + 76);

        this.gameData = new GameData(model, xResolution, yResolution);

        this.getContentPane().add(gameData);

        this.setVisible(true);
    }

}
