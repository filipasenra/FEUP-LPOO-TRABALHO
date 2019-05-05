package com.pac_xon;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            Game game = Game.getInstance();
            game.startMenu();
            game.run();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
