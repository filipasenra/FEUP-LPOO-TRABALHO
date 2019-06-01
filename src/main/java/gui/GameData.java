package gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.pac_xon.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameData extends JPanel {
    private Image wall;
    private Image player;
    private Image monster;

    private Model model;

    private int charactersWidth;
    private int charactersHeight;
    private int level;

    public GameData(Model model, int width, int height) throws IOException {
        this.model = model;

        setFocusable(true);
        setDoubleBuffered(true);
        loadImages();

        this.charactersWidth = width/model.getArena().getWidth();
        this.charactersHeight = height/(model.getArena().getHeight());
    }

    private void loadImages(){
        ImageIcon ii;

        ii = new ImageIcon("images/wall.jpg");
        wall = ii.getImage();

        ii = new ImageIcon("images/player.png");
        player = ii.getImage();

        ii = new ImageIcon("images/green_ghost.png");
        monster = ii.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());


        drawGame(g);
     }

    private void drawGame(Graphics g) {

        drawWalls(g);
        drawPLayer(g);
        drawMonsters(g);
    }

    private void drawCharacter(Image img, Graphics g, int i, int j){

        g.drawImage(img, i*this.charactersWidth, j*this.charactersHeight, charactersWidth, charactersHeight, this);

    }

    private void drawPLayer(Graphics g2d) {
        Player player2d = model.getArena().getPlayer();

        drawCharacter(player, g2d, player2d.getPosition().getX(), player2d.getPosition().getY());

    }

    private void drawMonsters(Graphics g2d) {
        List<Monster> monsters2d = model.getArena().getMonsters();

        if(monsters2d!=null){

            for(int i = 0; i < monsters2d.size(); i++){
                Monster monster2d = monsters2d.get(i);
                    drawCharacter(monster, g2d, monster2d.getPosition().getX(),monster2d.getPosition().getY());
            }
        }
    }

    private void drawWalls(Graphics g2d) {

        Wall.TYPE walls_array[][] = model.getArena().getWall().getWalls_array();

        for (int i = 0; i < model.getArena().getWidth(); i++) {
            for (int j = 0; j < model.getArena().getHeight(); j++) {

                if (walls_array[i][j] == Wall.TYPE.Wall || walls_array[i][j] == Wall.TYPE.Construction) {

                    drawCharacter(wall, g2d, i, j);
                }
            }
        }


    }
}
