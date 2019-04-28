import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.abs;

public class Game {
    private Screen screen;
    private Arena arena;
    private KeyStroke key;
    int FPS = 450; //Frames per seconds (controls the speed of the Player)
    private Menu menu;
    private int lives;
    private int no_monsters;

    //Tolerance for how much time (in milliseconds) it has passed since the key was pressed
    private static int TIME_FOR_KEY = 200;

    public Game() {
        try {
            int width = 70;
            int height = 20;
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(width, height)).createTerminal();
            this.screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            this.lives = 5;
            this.no_monsters = 2;

            this.arena = new Arena(width, height, lives, no_monsters, 0);
            this.menu = new Menu(width, height);

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

                if (this.key == null)
                    continue;

                if (this.key.getKeyType() == KeyType.Character && this.key.getCharacter() == 'q')
                    break;

                processKey(this.key);

                if (this.key.getKeyType() == KeyType.EOF)
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

            if (no_monsters < 10)
                no_monsters++;

            arena = new Arena(70, 20, arena.lives.getLives() +1, no_monsters, arena.score.getScore());

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

    private void processKey(KeyStroke key) throws IOException {

        //Tolerance for how much time (in milliseconds) it has passed since the key was pressed
        if(abs(key.getEventTime() - System.currentTimeMillis()) > TIME_FOR_KEY )
        {
            KeyStroke key_new = screen.pollInput();

            if(key_new == null)
                return;

            processKey(key_new);
            return;
        }

        arena.processKey(key);
        return;
    }

}