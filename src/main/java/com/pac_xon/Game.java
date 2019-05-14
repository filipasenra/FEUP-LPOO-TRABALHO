package com.pac_xon;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Game {
    private static  Game currentInstance;
    private JFrame frame;
    private Arena arena;
    private KeyStroke key;
    private int FPS = 10; //Frames per seconds (controls the speed of the com.pac_xon.Player)
    private Menu menu;
    private int lives;
    private int no_monsters;
    private int initTime;

    private Game() throws IOException {
            initTime = (int) (System.currentTimeMillis());
            int width = 600;
            int height = 600;
            this.frame = new JFrame("PAC-XON");
            this.frame.setSize(width, height);


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

    public void startMenu() throws IOException {

        menu.startGamemenu(frame);
       // screen.refresh();

        /*do {
            key = screen.readInput();

            if (this.key.getKeyType() == KeyType.Character && this.key.getCharacter() == 'q')
                screen.close();

            if (this.key.getKeyType() == KeyType.Enter) {
                break;
            }

        } while (key.getKeyType() != KeyType.EOF);*/

    }

    public void run() throws IOException, InterruptedException {

        /*do {
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
            screen.close();*/
    }

    private boolean isToBeContinue() throws IOException, InterruptedException {

       /* if (arena.isFinishLevel()) {
            //menu.nextLevelmenu(screen.newTextGraphics());
            screen.refresh();

            if (no_monsters < 10)
                no_monsters++;

            arena = new Arena(70, 20, arena.getLives().getLives() +1, no_monsters, arena.getScore().getScore());

            TimeUnit.SECONDS.sleep(2);

            return true;

        }

        //menu.gameOvermenu(screen.newTextGraphics(), arena.getScore().getScore());
        screen.refresh();

        TimeUnit.SECONDS.sleep(2);*/

        return false;
    }


    public void draw() throws IOException {

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
       frame.getContentPane().add(menu);

        frame.pack();
        frame.setVisible(true);

        /*screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();*/
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
        return;
    }

}