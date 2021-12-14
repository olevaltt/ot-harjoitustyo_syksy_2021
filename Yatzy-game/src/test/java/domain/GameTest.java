
package domain;



import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import yatzy.domain.Category;

import yatzy.domain.Game;


public class GameTest {
    
    Game testGame;
    
    public GameTest() {
    }
    
    @Before
    public void setUp() {
        testGame = new Game(4);
    }

    
    @Test
    public void getCurrentPlayerWorks() {
        assertEquals(testGame.getCurrentPlayer().getPlayerNumber(), 1);
    }
    
    @Test
    public void changeTurnWorks() {
        testGame.changeTurn();
        testGame.changeTurn();
        assertEquals(testGame.getCurrentPlayer().getPlayerNumber(), 3);
    }
    
    @Test
    public void getPlayerScoreWorks() {
        testGame.addPlayerScore(testGame.getCurrentPlayer(), Category.YATZY, 80);
        assertEquals(testGame.getPlayerScore(testGame.getCurrentPlayer(), Category.YATZY), 80);
    }

}
