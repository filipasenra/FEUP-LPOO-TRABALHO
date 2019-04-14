import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Game {
    private Screen screen;
    private Arena arena;
    private KeyStroke key;
    int FPS = 250; //Frames per seconds (controls the speed of the Player)

    public Game() {
        try {
            int width = 70;
            int height = 20;
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            arena = new Arena(width, height);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            do {
                screen.setCursorPosition(null);   // we don't need a cursor
                screen.startScreen();             // screens must be started
                screen.doResizeIfNecessary();     // resize screen if necessary

                draw();

                TimeUnit.MILLISECONDS.sleep((60* 1000)/FPS);

                key = screen.pollInput();

                if(key == null)
                    continue;

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                    screen.close();

                processKey(key);

                if(key.getKeyType() == KeyType.EOF)
                    break;

            } while (true);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

}