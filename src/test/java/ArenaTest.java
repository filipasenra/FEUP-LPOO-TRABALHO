import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
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
    public void checkPlayerWall()
    {

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

}
