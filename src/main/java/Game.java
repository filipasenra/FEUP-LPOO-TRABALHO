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
    int FPS = 450; //Frames per seconds (controls the speed of the Player)
    private Menu menu;
    private int lives;
    private int no_monsters;

    public Game() {
        try {
            int width = 70;
            int height = 20;
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessar

            lives = 5;
            no_monsters = 4;

            arena = new Arena(width, height, lives, no_monsters);

            menu = new Menu(width, height);
        } catch (IOException e) {
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

                TimeUnit.MILLISECONDS.sleep((60 * 1000) / FPS);

                key = screen.pollInput();

                if (key == null)
                    continue;

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                    break;

                processKey(key);

                if (key.getKeyType() == KeyType.EOF)
                    break;

            } while (!arena.isGameOver() && !arena.isFinishLevel());

            if(isToBeContinue())
                run();
            else
                screen.close();


        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isToBeContinue() throws IOException, InterruptedException {

        if (arena.isFinishLevel()) {
            menu.nextLevelmenu(screen.newTextGraphics());
            screen.refresh();

            if (lives > 2)
                lives--;

            if (no_monsters < 10)
                no_monsters++;

            arena = new Arena(70, 20, lives, no_monsters);

            TimeUnit.SECONDS.sleep(2);

            return true;

        }

        menu.gameOvermenu(screen.newTextGraphics());
        screen.refresh();

        TimeUnit.SECONDS.sleep(2);

        return false;
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