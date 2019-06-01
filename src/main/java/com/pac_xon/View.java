package com.pac_xon;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import gui.GameFrame;

import java.io.IOException;

public class View {

    private Screen screen;
    private Model model;
    private GameFrame gameFrame;

    public View(int width, int height, Model model) throws IOException {

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.model = model;

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        gameFrame = new GameFrame(model);
        gameFrame.requestFocus();
    }

    public void newGame() throws IOException {

        gameFrame.addingGame();
    }

    public void draw() throws IOException {
        screen.clear();
        model.getArena().draw(screen.newTextGraphics());
        screen.refresh();

        gameFrame.repaint();
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

        gameFrame.addingStartMenu();
    }

    public void startNextLevelMenu (Menu menu) throws IOException {

        menu.nextLevelmenu(screen.newTextGraphics());
        screen.refresh();
    }

    public void gameOverMenu(Menu menu, int score) throws IOException {

        menu.gameOvermenu(screen.newTextGraphics(), score);
        screen.refresh();
    }
}
