package gui;

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
    private JButton exitButton;

    public StartMenu(int width, int height) throws IOException {

        setFocusable(true);
        setDoubleBuffered(true);

        this.width = width;
        this.height = height;

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



        //Exit Button
        this.exitButton =   new JButton("Exit");
        this.exitButton.setFont(new Font("Courier New", Font.BOLD, 12));
        this.add(this.exitButton, gbc);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

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
