package com.pac_xon;

import com.pac_xon.Monster;
import com.pac_xon.Position;
import com.pac_xon.Wall;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class WallTest {

    Wall wall = new Wall(5, 6, " ", "ffffff");

    Wall.TYPE [][] expected;

    @Before
    public void start()
    {
        expected = new Wall.TYPE [][] {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};
    }

    private void showArray(Wall.TYPE[][] array)
    {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == Wall.TYPE.Wall)
                    System.out.print("W ");
                else if (array[i][j] == Wall.TYPE.Sea)
                    System.out.print(("S "));
                else if (array[i][j] == Wall.TYPE.Construction)
                    System.out.print(("C "));
                else
                    System.out.print(("Y "));

            }

            System.out.println(" ");
        }
    }

    @Test
    public void fillingWallTest() {
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);

        wall.fillWall(new ArrayList<>());

        Wall.TYPE [][] result_ = wall.getWalls_array();

        /*showArray(beginning);
        System.out.println(" ");
        showArray(result_);
        System.out.println(" ");*/

        assertTrue(Arrays.deepEquals(expected, result_));
    }

    @Test
    public void fillingWallTestV2(){
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);

        /*showArray(beginning);
        System.out.println(" ");*/

        wall.fillWall(new ArrayList<>());

        Wall.TYPE [][] result_ = wall.getWalls_array();

        /*showArray(result_);
        System.out.println(" ");*/

        assertTrue(Arrays.deepEquals(expected, result_));

    }

    @Test
    public void fillingWallTestWithMonster(){
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);

        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Monster(new Position(1, 1), "X", "ffffff");
        monsters.add(monster);

        wall.fillWall(monsters);

        Wall.TYPE [][] result_ = wall.getWalls_array();

        /*showArray(beginning);
        System.out.println(" ");
        showArray(result_);
        System.out.println(" ");*/

        assertTrue(Arrays.deepEquals(beginning, result_));


    }

    @Test
    public void fillingWallTestWithMonsterV2(){
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);
        /*showArray(beginning);
        System.out.println(" ");*/

        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Monster(new Position(1, 1), "X", "ffffff");
        monsters.add(monster);

        wall.fillWall(monsters);

        Wall.TYPE [][] result_ = wall.getWalls_array();

        expected = new Wall.TYPE [][] {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};


        /*showArray(result_);
        System.out.println(" ");*/

        assertTrue(Arrays.deepEquals(expected, result_));

    }

    @Test
    public void percentageFill() {
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);

        assertEquals(0.0,wall.percentage_fill());
    }

    @Test
    public void percentageFill2() {
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);
        wall.fillWall(new ArrayList<>());

        assertEquals(100.0, wall.percentage_fill());
    }

    @Test
    public void percentageFill3() {
        Wall.TYPE [][] beginning = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(beginning);

        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Monster(new Position(1, 1), "X", "ffffff");
        monsters.add(monster);

        wall.fillWall(monsters);

        assertEquals(75.0, wall.percentage_fill());
    }
}
