package com.pac_xon;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class View {

    private Screen screen;
    JFrame frame;

    public View(int width, int height) throws IOException {

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        this.screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary


        //Swing
        this.frame = new JFrame("Pac-Xon");
        frame.setSize(width*50, height*100);
        frame.setLayout( new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        //fechar
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void draw(Arena arena) throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();

        //swing
        frame.repaint();
    }

    public void closeScreen() throws IOException {
        screen.close();
    }

    public KeyStroke readInput() throws IOException {
        return screen.readInput();
    }

    public KeyStroke pollInput() throws IOException {
        return screen.pollInput();
    }

    public void startMenu(Menu menu) throws IOException {

        menu.startGamemenu(screen.newTextGraphics());
        screen.refresh();

        //Swing
        JLabel labelPacXon = new JLabel("Pac Xon", JLabel.CENTER);
        labelPacXon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelPacXon.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        frame.add(labelPacXon);

        JLabel label = new JLabel("New Game", JLabel.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        frame.add(label);

        JLabel labelPressEnter = new JLabel("Press enter to start", JLabel.CENTER);
        labelPressEnter.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelPressEnter.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
        frame.add(labelPressEnter);

        frame.repaint();


    }

    public void startNextLevelMenu (Menu menu) throws IOException {

        menu.nextLevelmenu(screen.newTextGraphics());
        screen.refresh();
    }

    public void gameOverMenu(Menu menu, int score) throws IOException {

        menu.gameOvermenu(screen.newTextGraphics(), score);
        screen.refresh();
    }

    void preparingGame(Arena arena){

        frame.getContentPane().removeAll();

        arena.addComponents(frame);
        frame.repaint();

    }

    public JFrame getFrame() {
        return frame;
    }
}
