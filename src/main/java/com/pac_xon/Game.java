package com.pac_xon;

import gui.ViewLanterna.ViewLanterna;
import gui.ViewSwing.View;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Game {
    private static  Game currentInstance;
    private int FPS = 10; //Frames per seconds (controls the speed of the Player)

    Model model;
    View view;
    ViewLanterna viewLanterna;

    private Game() throws IOException {

        int width = 70;
        int height = 20;

        this.model = new Model(width, height);
        this.view = new View(model);
        model.getArena().getScore().loadHighScores();
        this.viewLanterna = new ViewLanterna(width, height, model);
    }

    public static synchronized Game getInstance() throws IOException {
        if (currentInstance == null)
            currentInstance = new Game();

        return currentInstance;
    }

    public void startMenu() throws IOException, InterruptedException {

        view.startMenu();
        viewLanterna.startMenu();

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
            viewLanterna.draw();

            toolkit.sync();

        } while (!model.getArena().isGameOver() && !model.getArena().isFinishLevel() && model.menu_OPTION == Model.MENU.GAME);

        if (isToBeContinue())
            run();
        else
            view.closeScreen();

    }

    private boolean isToBeContinue() throws IOException, InterruptedException {

        if (model.getArena().isFinishLevel()) {

            view.startNextLevelMenu();
            viewLanterna.startNextLevelMenu();

            model.newLevel();

            TimeUnit.SECONDS.sleep(2);

            return true;

        }

        view.gameOverMenu();
        viewLanterna.gameOverMenu();
        model.getArena().getScore().loadHighScores();
        model.getArena().getScore().isHighScore(model.getArena().getScore());
        model.getArena().getScore().saveHighScores();

        TimeUnit.SECONDS.sleep(2);

        return false;
    }
}