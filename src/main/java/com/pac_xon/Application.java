package com.pac_xon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Application {
    public static void main(String[] args) throws InterruptedException {

        /*JFrame frame = new JFrame("Hello World Swing");
        frame.setSize(1000, 1000);
        //frame.setLayout(null);
        //fechar
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //centrar
       // frame.setLocationRelativeTo(null);

        JLabel label = new JLabel("Hello World");
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        label.setSize(300, 100);
        label.setLocation(500, 500);
        //frame.getContentPane().add(label);

        Menu menu = new Menu(1000, 1000);
        menu.setPreferredSize(new Dimension(900, 900));
        frame.getContentPane().add(menu);

        frame.pack();
        frame.setVisible(true);

        sleep(5000);*/


        try {
            Game game = Game.getInstance();
            //game.startMenu();
            game.run();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}