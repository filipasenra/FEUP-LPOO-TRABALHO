package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.pac_xon.Game;
import com.pac_xon.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameFrame extends JFrame {

    private final int xResolution = 980;
    private final int yResolution = 280 + 76 + 20;

    private GameData gameData;
    private ScoreData scoreData;

    private StartMenu startMenu;

    private JLabel gameOver;

    private Model model;

    public GameFrame(Model model){
        this.setTitle("Pac Xon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setBounds(0, 0, xResolution, yResolution);

        this.model = model;

    }
    public void addingGame() throws IOException {

        this.getContentPane().removeAll();

        this.setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)
        );

        //Plus 76 for the frame on top i think? + 20 to Score data
        this.gameData = new GameData(model, xResolution, yResolution - 74 - 22);
        this.gameData.setMaximumSize(new Dimension(xResolution, yResolution - 74 - 22));

        this.scoreData = new ScoreData(model, xResolution, yResolution);
        this.scoreData.setMaximumSize(new Dimension(xResolution, 22));

        this.getContentPane().add(scoreData);
        this.getContentPane().add(gameData);

        this.setVisible(true);

    }

    public void addingStartMenu() throws IOException {

        this.getContentPane().removeAll();

        //BackGround
        this.startMenu = new StartMenu(xResolution, yResolution);
        this.startMenu.setSize(xResolution, yResolution + 76 + 20);
        this.add(startMenu);

        this.setVisible(true);

    }

    public void addingGameOverMenu() throws IOException {
        this.gameData.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40,0,0,0); //Space between the buttons

        //Start Button
        this.gameOver = new JLabel("GameOver" + model.getArena().getScore().getScore());
        this.gameOver.setFont(new Font("Courier New", Font.BOLD, 12));
        this.gameOver.setForeground(Color.yellow);
        this.gameData.add(this.gameOver, gbc);

        this.setVisible(true);
    }

}
