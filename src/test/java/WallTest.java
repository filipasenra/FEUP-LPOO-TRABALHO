import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class WallTest {

    Wall wall = new Wall(5, 6, " ", "ffffff");

    Wall.TYPE [][] expected;

    @Before
    public void begging()
    {

        expected = new Wall.TYPE [][] {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};
    }

    private void showArray(Wall.TYPE[][] array)
    {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == Wall.TYPE.Wall)
                    System.out.print("1 ");
                else
                    System.out.print(("2 "));
            }

            System.out.println(" ");
        }
    }

    @Test
    public void fillingWallTest() {
        Wall.TYPE [][] begging = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(begging);

        wall.fillWall(new ArrayList<>());

        Wall.TYPE [][] result_ = wall.getWalls_array();

       /* showArray(begging);
        System.out.println(" ");
        showArray(result_);
        System.out.println(" ");*/

        boolean result = Arrays.deepEquals(result_, expected);

        assertEquals(true, result);

    }

    @Test
    public void fillingWallTestV2(){
        Wall.TYPE [][] begging = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(begging);

        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Monster(new Position(1, 1), " ", "ffffff");
        monsters.add(monster);

        wall.fillWall(monsters);

        Wall.TYPE [][] result_ = wall.getWalls_array();

        /*showArray(begging);
        System.out.println(" ");
        showArray(result_);
        System.out.println(" ");*/

        boolean result = Arrays.deepEquals(result_, begging);

        assertEquals(true, result);

    }

    @Test
    public void fillingWallTestWithMonster(){
        Wall.TYPE [][] begging = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(begging);

        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Monster(new Position(1, 1), "X", "ffffff");
        monsters.add(monster);

        wall.fillWall(monsters);

        Wall.TYPE [][] result_ = wall.getWalls_array();

        /*showArray(begging);
        System.out.println(" ");
        showArray(result_);
        System.out.println(" ");*/

        boolean result = Arrays.deepEquals(result_, begging);

        assertEquals(true, result);

    }

    @Test
    public void fillingWallTestWithMonsterV2(){
        Wall.TYPE [][] begging = {{Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Sea, Wall.TYPE.Construction, Wall.TYPE.Sea, Wall.TYPE.Sea, Wall.TYPE.Wall},
                {Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall, Wall.TYPE.Wall}};

        wall.setWalls_array(begging);

        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Monster(new Position(1, 1), "X", "ffffff");
        monsters.add(monster);

        wall.fillWall(monsters);

        Wall.TYPE [][] result_ = wall.getWalls_array();

        showArray(begging);
        System.out.println(" ");
        showArray(result_);
        System.out.println(" ");

        boolean result = Arrays.deepEquals(result_, begging);

        assertEquals(true, result);

    }
}
