package gui.ViewLanterna;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.pac_xon.Model;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class ViewLanterna {

    private Screen screen;
    private Model model;
    GameDataLanterna gameData;

    public ViewLanterna(int width, int height, Model model) throws IOException {

        Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
        this.screen = new TerminalScreen(terminal);
        this.model = model;

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        gameData = new GameDataLanterna(model);
    }


    public void draw() throws IOException {
        screen.clear();
        gameData.draw(screen.newTextGraphics());
        screen.refresh();
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

    public void startMenu() throws IOException {

        gameData.drawStartMenu(screen.newTextGraphics());
        screen.refresh();
    }

    public void startNextLevelMenu () throws IOException {

        gameData.drawNextLevelMenu(screen.newTextGraphics());
        screen.refresh();
    }

    public void gameOverMenu() throws IOException {

        gameData.drawGameOverMenu(screen.newTextGraphics());
        screen.refresh();
    }
}
