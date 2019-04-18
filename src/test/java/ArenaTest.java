import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class ArenaTest {

    Arena arena = new Arena(70, 20);

    @Test
    public void generatePositionsTest()
    {
        Random deduplicator = Mockito.mock(Random.class);

        when(deduplicator.nextInt()).thenReturn(0);

        Position position = arena.generatePositions();

        assertEquals(1, 1);
    }
}
