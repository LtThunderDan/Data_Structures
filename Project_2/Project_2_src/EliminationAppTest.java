import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EliminationAppTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTick1() {
        EliminationApp test = new EliminationApp();
        test.init(10,2);
        assertEquals("Failed when testing tick#1 with 10 players eliminating on 2", "player2", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#2 with 10 players eliminating on 2", "player4", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#3 with 10 players eliminating on 2", "player6", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#4 with 10 players eliminating on 2", "player8", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#5 with 10 players eliminating on 2", "player10", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#6 with 10 players eliminating on 2", "player3", test.tick().toLowerCase());
    }
    @Test
    public void testTick2() {
        EliminationApp test = new EliminationApp();
        test.init(10,25);
        assertEquals("Failed when testing tick#1 with 10 players eliminating on 25", "player5", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#2 with 10 players eliminating on 25", "player2", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#3 with 10 players eliminating on 25", "player3", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#4 with 10 players eliminating on 25", "player8", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#5 with 10 players eliminating on 25", "player9", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#6 with 10 players eliminating on 25", "player7", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#7 with 10 players eliminating on 25", "player10", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#8 with 10 players eliminating on 25", "player1", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#9 with 10 players eliminating on 25", "player4", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#10 with 10 players eliminating on 25", "player6", test.tick().toLowerCase());
        assertEquals("Failed when testing tick#11 with winner found", "player6", test.tick().toLowerCase());
    }

    @Test
    public void testCurrentIsWinner() {
        EliminationApp test = new EliminationApp();
        test.init(40,1);
        for(int i=0;i<40;i++){
            test.tick();
        }
        assertTrue("Failed when testing currentIsWinner",test.currentIsWinner());

    }

    @Test
    public void testGetEliminationNumber() {
        EliminationApp test = new EliminationApp();
        test.init(10,5);
        assertEquals("Testing getEliminationNumber when initialized to 5", 5, test.getEliminationNumber());
    }

    @Test
    public void testGetCurrentPlayerName()  {
        EliminationApp test = new EliminationApp();
        test.init(10,1);
        for(int i=0;i<10;i++){
            test.tick();
        }
        assertEquals("Testing winner found when 10 players eliminating on 1","player10",test.getCurrentPlayerName().toLowerCase());
    }
}