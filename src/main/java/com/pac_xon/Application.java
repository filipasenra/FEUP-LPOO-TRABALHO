package com.pac_xon;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        try {
            Game game = Game.getInstance();
            game.startMenu();
            System.out.println("Passou");
            game.run();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}