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

    Model model;
    View view;

    private Game() throws IOException {

        int width = 70;
        int height = 20;

        this.model = new Model(width, height);
        this.view = new View(width, height, model);
        model.getArena().getScore().loadHighScores();
    }

    public static synchronized Game getInstance() throws IOException {
        if (currentInstance == null)
            currentInstance = new Game();

        return currentInstance;
    }

    public void startMenu() throws IOException, InterruptedException {

        view.startMenu(model.getMenu());

        model.installKeyHandlerStartMenu();

        do {

            sleep(10);

        } while (model.menu_OPTION == Model.MENU.START_MENU);

    }

    private void updateFrame() throws IOException {

        model.getArena().move();
        view.draw();

    }

    public void run() throws IOException, InterruptedException {

        model.installKeyHandlerGame();
        view.newGame();

        model.menu_OPTION = Model.MENU.GAME;

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        do {
            sleep(1000/FPS);

            updateFrame();

            toolkit.sync();

        } while (!model.getArena().isGameOver() && !model.getArena().isFinishLevel() && model.menu_OPTION == Model.MENU.GAME);

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
        model.getArena().getScore().loadHighScores();
        model.getArena().getScore().isHighScore(model.getArena().getScore());
        model.getArena().getScore().saveHighScores();

        TimeUnit.SECONDS.sleep(2);

        return false;
    }

    public Model getModel() {
        return model;
    }
}