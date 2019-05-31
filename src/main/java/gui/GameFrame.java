package gui;

import javax.swing.*;

import com.pac_xon.Game;

import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {

    private final int xResolution = 800;
    private final int yResolution = 600;

    private GameData gameData;
    private Game game;

    public GameFrame() throws IOException {
        this.setTitle("Pac Xon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        gameData = new GameData(this);
        game = Game.getInstance();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setBounds((screenSize.width-xResolution)/2, (screenSize.height-yResolution)/2, xResolution, yResolution);

        this.getContentPane().add(gameData);

        this.setVisible(true);
    }

}
