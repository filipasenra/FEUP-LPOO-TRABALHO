package gui1.ViewSwing;

import com.pac_xon.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartMenu extends JPanel {

    private Image background;
    private int width;
    private int height;

    private JButton startButton;
    private JButton scores;
    private JButton exitButton;

    Model model;

    public StartMenu(Model model, int width, int height) throws IOException {

        setFocusable(true);
        setDoubleBuffered(true);

        this.width = width;
        this.height = height;
        this.model = model;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(40,0,0,0); //Space between the buttons

        GridBagConstraints name = new GridBagConstraints();
        name.gridwidth = GridBagConstraints.REMAINDER;
        name.fill = GridBagConstraints.PAGE_START;

        //Name of the game
        JLabel label = new JLabel("Pac Xon");
        label.setFont(new Font("Courier New", Font.PLAIN, 60));
        label.setForeground(Color.yellow);
        this.add(label, name);

        //Start Button
        this.startButton = new JButton("New Game");
        this.startButton.setFont(new Font("Courier New", Font.BOLD, 12));
        this.add(this.startButton, gbc);

        this.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {  model.menu_OPTION = Model.MENU.GAME;} } );

        //HighScores Button
        this.scores = new JButton("High Scores");
        this.scores.setFont(new Font("Courier New", Font.BOLD, 12));
        this.add(this.scores, gbc);

        this.scores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String msg = "";

                for (int i : model.getArena().getScore().getHighScores()){
                    msg = msg + i + "<br>";
                }

                JLabel message = new JLabel("<html><center>" + msg, JLabel.CENTER);

                JOptionPane.showMessageDialog(null, message, "TOP 5 HIGH SCORES", JOptionPane.PLAIN_MESSAGE);
            } } );

        //Exit Button
        this.exitButton =   new JButton("Exit");
        this.exitButton.setFont(new Font("Courier NeDEFAULT_OPTIONw", Font.BOLD, 12));
        this.add(this.exitButton, gbc);

        this.exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { String msg = "Are you sure you want to Exit?";
                int res = JOptionPane.showConfirmDialog(null, msg, null, JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) System.exit(0); } } );

        loadImages();

    }

    void loadImages(){

        ImageIcon ii;

        ii = new ImageIcon("images/background.jpg");
        background = ii.getImage();

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

       graphics.drawImage(background, 0, 0, this.width, this.height, this);

    }
}
