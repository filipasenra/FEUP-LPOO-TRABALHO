package com.pac_xon;

import com.pac_xon.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.event.KeyEvent;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ArenaTest {

    @Test
    public void arenaCollisionTest()
    {
        Arena arena;
        Player player;
        Wall wall;

        player = Mockito.mock(Player.class);
        wall = Mockito.mock(Wall.class);
        arena = new Arena(5, 6,player,wall);

        when(player.getPosition()).thenReturn(new Position(1, 1));
        when(wall.getWall(any(int.class), any(int.class))).thenReturn(Wall.TYPE.Construction);

        assertTrue(arena.checkCollision(new Position(1, 1)));
    }

    @Test
    public void checkMonsterMove() {
        Position position = new Position(2, 2);
        Monster monster = new Monster(position, "X", "#ffffff");

        Wall wall = new Wall(5, 6, " ", "ffffff");
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);

        Player player = null;
        Arena arena = new Arena(5, 6, player, wall);
        arena.monsterMove(monster);

        assertEquals(-1, monster.getSpeed_x());
        assertEquals(1, monster.getSpeed_y());
    }

    @Test
    public void checkProcessKeyDown(){

        Wall wall = new Wall(5, 6, " ", "ffffff");
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        Player player = new Player(new Position(0, 0), "C", "#ffffff");

        Arena arena = new Arena(5, 6, player, wall);

        arena.processKeySwing(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_DOWN, 'a'));

        assertEquals(player.getDirection(), Player.DIRECTION.SOUTH);
    }

    @Test
    public void checkProcessKeyUp(){

        Wall wall = new Wall(5, 6, " ", "ffffff");
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        Player player = new Player(new Position(0, 0), "C", "#ffffff");

        Arena arena = new Arena(5, 6, player, wall);

        arena.processKeySwing(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_UP, 'a' ));

        assertEquals(player.getDirection(), Player.DIRECTION.NORTH);
    }

    @Test
    public void checkProcessKeyLeft(){

        Wall wall = new Wall(5, 6, " ", "ffffff");
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        Player player = new Player(new Position(0, 0), "C", "#ffffff");

        Arena arena = new Arena(5, 6, player, wall);

        arena.processKeySwing(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_LEFT, 'a'));

        assertEquals(player.getDirection(), Player.DIRECTION.WEST);
    }

    @Test
    public void checkProcessKeyRight(){

        Wall wall = new Wall(5, 6, " ", "ffffff");
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        Player player = new Player(new Position(0, 0), "C", "#ffffff");

        Arena arena = new Arena(5, 6, player, wall);

        arena.processKeySwing(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_RIGHT, 'a'));

        assertEquals(player.getDirection(), Player.DIRECTION.EAST);
    }

    @Test
    public void checkProcessKeyNotAccepted(){


        Wall wall = new Wall(5, 6, " ", "ffffff");

        Player player = new Player(new Position(0, 0), "C", "#ffffff");

        Player.DIRECTION direction = player.getDirection();

        Arena arena = new Arena(5, 6, player, wall);

        arena.processKeySwing(new KeyEvent(new Component() {}, 0, 0, 0, KeyEvent.VK_RIGHT, 'a'));

        assertEquals(player.getDirection(), direction);

    }

    @Test
    public void checkPlayerMovementV1(){

        Wall wall = new Wall(5, 6, " ", "ffffff");

        Player player = new Player(new Position(0, 0), "C", "#ffffff");

        Player.DIRECTION direction = player.getDirection();

        Arena arena = new Arena(5, 6, player, wall);

        assertTrue(arena.playerMove(player.getPosition()));

        assertEquals(arena.getPlayer().getPosition().getX(), 0);

        assertEquals(arena.getPlayer().getPosition().getY(), 0);

    }

    @Test
    public void checkPlayerMovementV2(){

        Wall wall = new Wall(5, 6, " ", "ffffff");

        Player player = new Player(new Position(1, 1), "C", "#ffffff");

        Arena arena = new Arena(5, 6, player, wall);

        assertTrue(arena.playerMove(player.getPosition()));

        assertEquals(2, arena.getPlayer().getPosition().getX());

        assertEquals(1, arena.getPlayer().getPosition().getY());

    }


    @Test
    public void checkPlayerMovementV3(){

        Wall wall = new Wall(5, 6, " ", "ffffff");
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);
        Player player = new Player(new Position(1, 1), "C", "#ffffff");

        Arena arena = new Arena(5, 6, player, wall);

        assertFalse(arena.playerMove(player.getPosition()));

    }

    @Test
    public void checkPlayerMovementV4(){

        Wall wall = new Wall(5, 6, " ", "ffffff");

        Player player = new Player(new Position(0, 0), "C", "#ffffff");

        Arena arena = new Arena(5, 6, player, wall);

        assertTrue(arena.playerMove(new Position(2, 2)));

    }

}
