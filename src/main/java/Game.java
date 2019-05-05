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
    private static  Game currentInstance;
    private Screen screen;
    private Arena arena;
    private KeyStroke key;
    private int FPS = 10; //Frames per seconds (controls the speed of the Player)
    private Menu menu;
    private int lives;
    private int no_monsters;
    private int initTime;
    private boolean started = false;

    private Game() throws IOException {
            initTime = (int) (System.currentTimeMillis());
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
    }

    public static synchronized Game getInstance() throws IOException {
        if (currentInstance == null)
            currentInstance = new Game();

        return currentInstance;
    }

    private void startMenu() throws IOException {

        menu.startGamemenu(screen.newTextGraphics());
        screen.refresh();

        do {
            key = screen.readInput();

            if (this.key.getKeyType() == KeyType.Character && this.key.getCharacter() == 'q')
                screen.close();

            if (this.key.getKeyType() == KeyType.Enter) {
                break;
            }

        } while (key.getKeyType() != KeyType.EOF);

    }

    public void run() throws IOException, InterruptedException {

        startMenu();

        do {
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary


            if (((System.currentTimeMillis() - initTime) % (1000 / FPS)) == 0) {
                arena.move();
                draw();
            }

            key = screen.pollInput();

            if (this.key == null)
                continue;

            if (this.key.getKeyType() == KeyType.Character && this.key.getCharacter() == 'q')
                break;

            processKey(this.key);

            if (this.key.getKeyType() == KeyType.EOF)
                break;

        } while (!arena.isGameOver() && !arena.isFinishLevel());

        if (isToBeContinue())
            run();
        else
            screen.close();
    }

    private boolean isToBeContinue() throws IOException, InterruptedException {

        if (arena.isFinishLevel()) {
            menu.nextLevelmenu(screen.newTextGraphics());
            screen.refresh();

            if (no_monsters < 10)
                no_monsters++;

            arena = new Arena(70, 20, arena.getLives().getLives() +1, no_monsters, arena.getScore().getScore());

            TimeUnit.SECONDS.sleep(2);

            return true;

        }

        menu.gameOvermenu(screen.newTextGraphics(), arena.getScore().getScore());
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
        return;
    }

}