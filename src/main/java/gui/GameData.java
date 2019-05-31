package gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.pac_xon.Game;
import com.pac_xon.Monster;
import com.pac_xon.Player;
import com.pac_xon.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameData extends JPanel {
    private Image background;
    private Image wall;
    private Image player;
    private Image monster;

    private GameFrame gameFrame;
    private Game game;

    private int charactersWidth;
    private int charactersHeight;
    private int level;

    public GameData(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setFocusable(true);
        setDoubleBuffered(true);
        loadImages();
    }

    private void loadImages(){
        ImageIcon temp; temp = new ImageIcon(this.getClass().getResource("images/background.png")); background = temp.getImage();

        temp = new ImageIcon(this.getClass().getResource("images/wall.png")); wall = temp.getImage();

        temp = new ImageIcon(this.getClass().getResource("images/player.png")); player = temp.getImage();

        temp = new ImageIcon(this.getClass().getResource("images/monster.png")); monster = temp.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawGame(g2d);
     }

    private void drawGame(Graphics2D g2d) {
        drawPLayer(g2d);
        drawMonsters(g2d);
        drawWalls(g2d);
    }

    private void drawCharacter(Image img, Graphics g2d, int i, int j){
        int distX = j * charactersWidth; int distY = i * charactersHeight;

        distX += (getWidth() - charactersWidth);
        distY += (getHeight() - charactersHeight);

        g2d.drawImage(img, distX, distY, distX + charactersWidth, distY + charactersHeight, 0, 0, img.getWidth(null), img.getHeight(null), null);}

    private void drawPLayer(Graphics2D g2d) {
        Player player2d = game.getModel().getArena().getPlayer();

        drawCharacter(player, g2d, player2d.getPosition().getX(), player2d.getPosition().getY());

    }

    private void drawMonsters(Graphics2D g2d) {
        List<Monster> monsters2d = game.getModel().getArena().getMonsters();

        if(monsters2d!=null){

            for(int i = 0; i < monsters2d.size(); i++){
                Monster monster2d = monsters2d.get(i);
                    drawCharacter(monster, g2d, monster2d.getPosition().getX(),monster2d.getPosition().getY());
            }
        }
    }

    private void drawWalls(Graphics2D g2d) {

    }
}
