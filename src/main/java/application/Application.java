package application;

import com.pac_xon.Game;

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