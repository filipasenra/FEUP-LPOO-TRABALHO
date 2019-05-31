package com.pac_xon;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Game {
    private static  Game currentInstance;
    private int FPS = 10; //Frames per seconds (controls the speed of the Player)
    private int initTime;

    Model model;
    View view;

    private Game() throws IOException {
            initTime = (int) (System.currentTimeMillis());
            int width = 70;
            int height = 20;

            this.view = new View(width, height);
            this.model = new Model(width, height);
    }

    public static synchronized Game getInstance() throws IOException {
        if (currentInstance == null)
            currentInstance = new Game();

        return currentInstance;
    }

    public void startMenu() throws IOException {

        KeyStroke key;

        view.startMenu(model.getMenu());


        do {
            key = view.readInput();

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                view.closeScreen();

            if (key.getKeyType() == KeyType.Enter) {
                break;
            }

        } while (key.getKeyType() != KeyType.EOF);

    }

    public void run() throws IOException, InterruptedException {

        model.installKeyHandler();

        KeyStroke key;

        do {


            if (((System.currentTimeMillis() - initTime) % (1000 / FPS)) == 0) {
                model.getArena().move();
                view.draw(model.getArena());
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