package com.pac_xon;

import com.pac_xon.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckMonsterMoveTest {
    Arena arena;
    Wall wall;

    @Before
    public void setUp(){

        wall = new Wall(5, 6);
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);

        Player player = null;
        arena = new Arena(5, 6, player, wall);

    }

    @Test
    public void checkMonsterMoveV1() {
        //Check when colliding with point of Square outside

        Position position = new Position(1, 1);
        Monster monster = new Monster(position);

        arena.monsterMove(monster);

        assertEquals(1, monster.getSpeed_x());
        assertEquals(1, monster.getSpeed_y());
    }

    @Test
    public void checkMonsterMoveV2() {
        //Check when colliding with point of Square outside


        Position position = new Position(3, 2);
        Monster monster = new Monster(position);

        arena.monsterMove(monster);

        assertEquals(-1, monster.getSpeed_x());
        assertEquals(-1, monster.getSpeed_y());
    }

    @Test
    public void checkMonsterMoveV3() {
        //Check when colliding with point of Square outside


        Position position = new Position(3, 2);
        Monster monster = new Monster(position);

        arena.monsterMove(monster);

        assertEquals(-1, monster.getSpeed_x());
        assertEquals(-1, monster.getSpeed_y());
    }
}
