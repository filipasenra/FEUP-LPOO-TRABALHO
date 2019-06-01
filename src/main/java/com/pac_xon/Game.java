package com.pac_xon;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Game {
    private static  Game currentInstance;
    private int FPS = 10; //Frames per seconds (controls the speed of the Player)
    private int initTime;

    Model model;
    View view;

    boolean startGame = false;

    private Game() throws IOException {
            initTime = (int) (System.currentTimeMillis());
            int width = 70;
            int height = 20;

        this.model = new Model(width, height);
            this.view = new View(width, height, model);

    }

    public static synchronized Game getInstance() throws IOException {
        if (currentInstance == null)
            currentInstance = new Game();

        return currentInstance;
    }

    public void startMenu() throws IOException, InterruptedException {

        view.startMenu(model.getMenu());

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {

                if(e.getID() == KeyEvent.KEY_RELEASED )
                    return false;

                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    startGame = true;
                }

                return false;
            }
        };

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        do {

            sleep(10);

        } while (!this.startGame);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(keyEventDispatcher);

    }

    public void run() throws IOException, InterruptedException {

        model.installKeyHandler();
        view.newGame();

        do {


            if (((System.currentTimeMillis() - initTime) % (1000 / FPS)) == 0) {
                model.getArena().move();
                view.draw();
            }

        } while (!model.getArena().isGameOver() && !model.getArena().isFinishLevel() && model.Play);

        if (isToBeContinue())
            run();
        else
            view.closeScreen();
    }

    private boolean isToBeContinue() throws IOException, InterruptedException {

        if (model.getArena().isFinishLevel()) {

            view.startNextLevelMenu(model.getMenu());

            model.newLevel();

            TimeUnit.SECONDS.sleep(2);

            return true;

        }

        view.gameOverMenu(model.getMenu(), model.getArena().getScore().getScore());

        TimeUnit.SECONDS.sleep(2);

        return false;
    }

    public Model getModel() {
        return model;
    }
}