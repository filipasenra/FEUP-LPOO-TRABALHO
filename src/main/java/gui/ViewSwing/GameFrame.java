package gui.ViewSwing;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

import com.pac_xon.Model;

public class GameFrame extends JFrame {

    private final int xResolution = 1260;
    private final int yResolution = 400 + 76;

    private GameData gameData;
    private ScoreData scoreData;

    public gui1.ViewSwing.StartMenu startMenu;

    private JLabel gameOver;

    private Model model;

    public GameFrame(Model model){
        this.setTitle("Pac Xon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

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
        this.startMenu = new gui1.ViewSwing.StartMenu(model, xResolution, yResolution);
        this.startMenu.setSize(xResolution, yResolution + 76 + 20);
        this.add(startMenu);

        this.setVisible(true);

    }

    public void addingGameOverMenu() {
        this.gameData.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,0,0,0); //Space between the buttons

        //Game Over
        this.gameOver = new JLabel("Game Over");
        this.gameOver.setFont(new Font("Courier New", Font.BOLD, 12));
        this.gameOver.setForeground(Color.yellow);
        this.gameData.add(this.gameOver, gbc);

        //Score
        this.gameOver = new JLabel("Score: " + model.getArena().getScore().getScore());
        this.gameOver.setFont(new Font("Courier New", Font.BOLD, 12));
        this.gameOver.setForeground(Color.yellow);
        this.gameData.add(this.gameOver, gbc);

        this.setVisible(true);
    }

    public void addingNextLevelMenu(){

        this.gameData.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10,0,0,0); //Space between the buttons

        //Next Level
        this.gameOver = new JLabel("Next Level!");
        this.gameOver.setFont(new Font("Courier New", Font.BOLD, 12));
        this.gameOver.setForeground(Color.yellow);
        this.gameData.add(this.gameOver, gbc);

        //Score
        this.gameOver = new JLabel("Score: " + model.getArena().getScore().getScore());
        this.gameOver.setFont(new Font("Courier New", Font.BOLD, 12));
        this.gameOver.setForeground(Color.yellow);
        this.gameData.add(this.gameOver, gbc);

        this.setVisible(true);
    }
}
