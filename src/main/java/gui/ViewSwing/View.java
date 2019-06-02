package gui.ViewSwing;

import com.pac_xon.Model;

import java.awt.event.WindowEvent;
import java.io.IOException;

public class View {

    public GameFrame gameFrame;

    public View(Model model) {

        gameFrame = new GameFrame(model);
        gameFrame.requestFocus();
    }

    public void newGame() throws IOException {

        gameFrame.addingGame();
    }

    public void draw() {

        gameFrame.repaint();
    }

    public void closeScreen(){

        gameFrame.dispatchEvent(new WindowEvent(gameFrame, WindowEvent.WINDOW_CLOSING));
    }
    public void startMenu() throws IOException {

        gameFrame.addingStartMenu();
    }

    public void startNextLevelMenu () {

      this.gameFrame.addingNextLevelMenu();
      this.gameFrame.repaint();

    }

    public void gameOverMenu() {

        this.gameFrame.addingGameOverMenu();
        this.gameFrame.repaint();
    }
}
