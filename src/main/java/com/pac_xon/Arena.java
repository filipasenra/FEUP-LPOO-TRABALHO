package com.pac_xon;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Arena {
    private int width;
    private int height;
    private BackGround background;
    private Player player;
    private Wall wall;
    private List<Monster> monsters = new ArrayList<>();
    private Percentage percentage;
    private Score score;
    private int lives;

    boolean finishLevel = false;

    private void init(int width, int height, int no_monsters) {
        this.width = width;
        this.height = height - 1;
        this.background = new BackGround(width, height, " ","#3f3832");
        this.percentage = new Percentage("%/80%", "#000000", width);
        this.score = new Score("Score: ", "#000000", width);

        createMonsters(no_monsters);
    }

    public Arena(int width, int height, Player p, Wall w) {
        init(width,height, 2);
        this.player = p;
        this.wall = w;
        lives = 4;
    }

    public Arena(int width, int height, int lives, int no_monsters, int score) {
        init(width,height, no_monsters);
        player = new Player(new Position(0,0), "C", "#FFFF33");
        wall = new Wall(this.width, this.height, " ", "#000080");
        this.lives = lives;
        this.score = new Score("Score: ", "#000000", width);
        this.score.setScore(score);
    }

    private void createMonsters(int no_monsters) {
        for (int i=0; i<no_monsters; i++)
            monsters.add(new Monster(generatePositions(), "X", "#FFCCD5"));
    }

    public Position generatePositions() {
        // create instance of Random class
        Random rand = new Random();

        int x = rand.nextInt(width-3) + 2;
        int y = rand.nextInt(height-3) + 2;

        return new Position(x, y);
    }

    public void move()
    {
        playerMove(player.getPosition());

        for (Monster monster: monsters) {

            monsterMove(monster);

            if (checkCollision(monster.getPosition()))
                resetGame();
        }

        this.percentage.setPercentage(wall.percentage_fill());

        if(wall.percentage_fill() >= 80)
            finishLevel = true;
    }

    private void resetGame()
    {
        if(lives == 0)
            return;

        lives--;

        player.setPosition(new Position(0, 0));

        wall.eraseConstructionSea(Wall.TYPE.Construction);
    }

    private boolean canPlayerMove(Position position)
    {
        if(position.getX() < 0 || position.getX() >= width)
            return false;

        if(position.getY() < 0 || position.getY() >= height)
            return false;

        return true;
    }

    public boolean playerMove(Position position) {

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Wall)
            return true;

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Construction) {
            resetGame();
            return false;
        }

        wall.addPath(position.getX(), position.getY());

        if (canPlayerMove(player.move()))
            player.setPosition(player.move());
        
        if (wall.getWall(player.getPosition().getX(), player.getPosition().getY()) == Wall.TYPE.Wall) {
            wall.fillWall(monsters);
            score.setScore(score.getScore()+ (int)wall.percentage_fill());
        }


        return true;
    }

    //The monsters walk in diagonals and change direction every time it hits a wall
    public void monsterMove(Monster monster) {
        Position position = monster.move();

        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Wall) {
            monster.changeMov(Monster.TYPE_WALL.Sides);
            monster.changeMov(Monster.TYPE_WALL.Tops);

            monster.setPosition(monster.move());
            return;
        }

        monster.setPosition(monster.move());

        //Check monsters' collisions with the walls
        if (wall.getWall(position.getX() + 1, position.getY()) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Sides);

        if (wall.getWall(position.getX() - 1, position.getY()) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Sides);

        if (wall.getWall(position.getX(), position.getY() + 1) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Tops);

        if (wall.getWall(position.getX(), position.getY() - 1) == Wall.TYPE.Wall)
            monster.changeMov(Monster.TYPE_WALL.Tops);
    }

    //Check if a monster touched a construction wall
    public boolean checkCollision (Position position) {
        if (wall.getWall(position.getX(), position.getY()) == Wall.TYPE.Construction || player.getPosition().equals(position)) {
            return true;
        }

        return false;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowDown:
                this.player.setDirection(Player.DIRECTION.SOUTH);
                break;
            case ArrowLeft:
                this.player.setDirection(Player.DIRECTION.WEST);
                break;
            case ArrowRight:
                this.player.setDirection(Player.DIRECTION.EAST);
                break;
            case ArrowUp:
                this.player.setDirection(Player.DIRECTION.NORTH);
                break;

            default:
                return;
        }

        if(wall.getWall(player.position.getX(), player.position.getY()) == Wall.TYPE.Wall)
        {
            if(canPlayerMove(player.move()))
                player.setPosition(player.move());
        }
    }

    public void processKeySwing(KeyEvent key) {

        switch (key.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                this.player.setDirection(Player.DIRECTION.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                this.player.setDirection(Player.DIRECTION.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                this.player.setDirection(Player.DIRECTION.EAST);
                break;
            case KeyEvent.VK_UP:
                this.player.setDirection(Player.DIRECTION.NORTH);
                break;

            default:
                return;
        }

        if(wall.getWall(player.position.getX(), player.position.getY()) == Wall.TYPE.Wall)
        {
            if(canPlayerMove(player.move()))
                player.setPosition(player.move());
        }
    }

    public boolean isGameOver() {
        return lives == 0;
    }

    public boolean isFinishLevel() {
        return finishLevel;
    }

    public Score getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public Percentage getPercentage() {
        return percentage;
    }

    public Player getPlayer() {return player; }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Wall getWall() {
        return wall;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
