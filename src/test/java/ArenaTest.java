import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ArenaTest {

    Arena arena = new Arena(5, 6);
    Player player;
    Wall wall;

    @Before
    public void setUp()
    {

        player = Mockito.mock(Player.class);
        wall = Mockito.mock(Wall.class);

        when(player.getPosition()).thenReturn(new Position(1, 1));
        when(wall.getWall(any(int.class), any(int.class))).thenReturn(Wall.TYPE.Construction);
    }

    @Test
    public void arenaCollisionTest()
    {
        assertFalse(arena.canMonsterMove(new Position(1, 1)));
    }

}
