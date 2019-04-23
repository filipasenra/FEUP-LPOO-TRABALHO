import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ArenaTest {

    Arena arena;
    Player player;
    Wall wall;

    @Test
    public void arenaCollisionTest()
    {
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

}
