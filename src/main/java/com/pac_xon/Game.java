package com.pac_xon;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Game {
    private static  Game currentInstance;
    private JFrame frame;
    private Arena arena;
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

    public void startMenu() throws IOException, InterruptedException {

        frame.add(menu);
        frame.setVisible(true);

        final CountDownLatch latch = new CountDownLatch(1);

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_Q)
                {
                    frame.setVisible(false);
                    latch.countDown();
                    return false;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    latch.countDown();
                    return true;
                }

                return true;
            }
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);
        latch.await();  // current thread waits here until countDown() is called
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);
        frame.remove(menu);


    }

    public void run() throws IOException, InterruptedException {

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                processKey(e);

                if (e.getKeyCode() == KeyEvent.VK_Q)
                {
                    frame.setVisible(false);
                }

                return false;
            }
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        do {

            if (((System.currentTimeMillis() - initTime) % (1000 / FPS)) == 0) {
                arena.move();
                draw();
            }

        } while (!arena.isGameOver() && !arena.isFinishLevel() && frame.isShowing());

        if (isToBeContinue())
            run();
        else
            frame.setVisible(false);
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
        arena.draw(frame);

        frame.pack();
        frame.setVisible(true);

        /*screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();*/
    }

    private void processKey(KeyEvent key) {
        arena.processKey(key);
        return;
    }

}